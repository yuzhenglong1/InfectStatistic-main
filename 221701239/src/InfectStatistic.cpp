/*
该代码功能:实现全国肺炎疫情情况的统计
作者:何瑞晨 学号:221701239
*/

#include<stdio.h>
#include<iostream>
#include<fstream>
#include<filesystem>
#include <string>
#include<string.h>
#include <io.h>  
#include <vector>  
#include<map>
#include <windows.h>
#include<sstream>

using namespace std;
map<string, string> m_single;      //存储命令行只有一个值的元素的信息
map<string, vector<string>> m_many;   //存储命令行可能有多个值的元素的信息
									  //每个省包括全国的肺炎信息类
map<string, vector<int>> m_province;  //存储每个省的肺炎相关信息,(感染患者,疑似患者,治愈数,死亡数)


									  //将字符转utf-8
string UTF8ToGB(const char* str)
{
	string result;
	WCHAR *strSrc;
	LPSTR szRes;

	//获得临时变量的大小
	int i = MultiByteToWideChar(CP_UTF8, 0, str, -1, NULL, 0);
	strSrc = new WCHAR[i + 1];
	MultiByteToWideChar(CP_UTF8, 0, str, -1, strSrc, i);

	//获得临时变量的大小
	i = WideCharToMultiByte(CP_ACP, 0, strSrc, -1, NULL, 0, NULL, NULL);
	szRes = new CHAR[i + 1];
	WideCharToMultiByte(CP_ACP, 0, strSrc, -1, szRes, i, NULL, NULL);

	result = szRes;
	delete[]strSrc;
	delete[]szRes;

	return result;
}

//往map中插入一个pair
void insert_province(string province_name)
{
	int i;
	vector<int> list;
	for (i = 0; i < 4; i++)
		list.push_back(0);
	m_province.insert(make_pair(province_name, list));
}

//从字符串中提取数字
int draw_number(string temp)
{
	stringstream s1(temp);
	int itemp;
	s1 >> itemp;
	return itemp;
}

//该函数实现了对命令行信息的处理
void process_cmd(int num, char* cmd_i[])
{
	int i;
	string temp;   //中转字符串
	for (i = 3; i < num; i++)
	{
		if (strcmp(cmd_i[i], "-province") == 0 || strcmp(cmd_i[i], "-type") == 0)
		{
			vector<string> list_temp;
			string m_name = cmd_i[i];//记录命令行参数名称
			temp = cmd_i[i];
			while (temp.compare("-") != 0)
			{
				temp = cmd_i[++i];
				list_temp.push_back(temp);
				cout << cmd_i[i] << "\n";
				if (i >= num - 1)
					break;
				temp = temp.substr(0, 1);
			}
			m_many.insert(make_pair(m_name, list_temp));
		}
		else
		{
			string temp1, temp2;
			temp1 = cmd_i[i];
			temp2 = cmd_i[++i];
			m_single.insert(make_pair(temp1, temp2));
		}
	}
}

//处理新增的情况
void process_xin_zeng(fstream& file, string province_name)
{
	string temp;
	file >> temp;
	temp = UTF8ToGB(temp.c_str()).c_str();
	if (temp.compare("感染患者") == 0)
	{
		file >> temp;
		int num = draw_number(temp);
		(m_province["全国"])[0] += num;
		(m_province[province_name])[0] += num;
	}
	else
	{
		file >> temp;
		int num = draw_number(temp);
		(m_province["全国"])[1] += num;
		(m_province[province_name])[1] += num;
	}
}

//处理治愈的情况
void process_zhi_yu(fstream& file, string province_name)
{
	string temp;
	file >> temp;
	int num = draw_number(temp);
	(m_province["全国"])[2] += num;
	(m_province[province_name])[2] += num;
	(m_province["全国"])[0] -= num;
	(m_province[province_name])[0] -= num;
}

//处理死亡的情况
void process_pass_away(fstream& file, string province_name)
{
	string temp;
	file >> temp;
	int num = draw_number(temp);
	(m_province["全国"])[3] += num;
	(m_province[province_name])[3] += num;
	(m_province["全国"])[0] -= num;
	(m_province[province_name])[0] -= num;
}

//处理流入的情况
void process_liu_ru(fstream& file, string province_name, string type_name)
{
	string liu_ru_province;
	file >> liu_ru_province;				//读出流入省份
	liu_ru_province = UTF8ToGB(liu_ru_province.c_str()).c_str();
	string temp;
	file >> temp;
	int num = draw_number(temp);
	if (type_name.compare("感染患者") == 0)
	{
		(m_province[province_name])[0] -= num;
		(m_province[liu_ru_province])[0] += num;
	}
	else
	{
		(m_province[province_name])[1] -= num;
		(m_province[liu_ru_province])[1] += num;
	}
}

//处理确诊感染的情况
void process_que_zhen(fstream& file, string province_name)
{
	string temp;
	file >> temp;
	int num = draw_number(temp);
	(m_province["全国"])[0] += num;
	(m_province["全国"])[1] -= num;
	(m_province[province_name])[0] += num;
	(m_province[province_name])[1] -= num;
}

//处理排除的情况
void process_pai_chu(fstream& file, string province_name)
{
	string temp;
	file >> temp;
	file >> temp;
	int num = draw_number(temp);
	(m_province["全国"])[1] -= num;
	(m_province[province_name])[1] -= num;

}

//按字符串读取并处理文件
void process_file(string log_file)
{
	int num;   //测试用的
			   //6种情况,新增(感染患者,疑似患者),感染患者,疑似患者(流入,确诊),死亡,治愈,排除(疑似患者).
	int i;
	//首先加入全国的情况
	fstream fei_yan_log(log_file);
	string temp;
	string temp_past;   //用于存储之前一次的读取
	string province_name;
	bool is_province = true; //是否读到省份的标志位
	while (!fei_yan_log.eof())
	{
		temp_past = temp;
		fei_yan_log >> temp;
		num = draw_number(temp);
		temp = UTF8ToGB(temp.c_str()).c_str();
		if (temp.substr(0, 1).compare("/") == 0)
			break;
		if (is_province)
		{
			if (m_province.find(temp) == m_province.end())
				insert_province(temp);
			is_province = false;
			province_name = temp;
		}
		if (temp.compare("新增") == 0)
		{
			process_xin_zeng(fei_yan_log, province_name);
			is_province = true;
		}
		if (temp.compare("死亡") == 0)
		{
			process_pass_away(fei_yan_log, province_name);
			is_province = true;
		}
		if (temp.compare("治愈") == 0)
		{
			process_zhi_yu(fei_yan_log, province_name);
			is_province = true;
		}
		if (temp.compare("流入") == 0)
		{
			process_liu_ru(fei_yan_log, province_name, temp_past);
			is_province = true;
		}
		if (temp.compare("确诊感染") == 0)
		{
			process_que_zhen(fei_yan_log, province_name);
			is_province = true;
		}
		if (temp.compare("排除") == 0)
		{
			process_pai_chu(fei_yan_log, province_name);
			is_province = true;
		}

	}
}

//将string日期转化为int数组日期
vector<int> transform_date(string date)
{
	int i;
	vector<int> int_temp;
	string temp=date;
	int itemp;
	for (i = 0; i < date.length(); i++)
	{
		if (date[i] == '-')
		{
			stringstream s(temp);
			s >> itemp;
			int_temp.push_back(itemp);
			temp = date.substr(++i, date.length());
		}
	}
	stringstream s(temp);
	s >> itemp;
	int_temp.push_back(itemp);
	return int_temp;
	

}

//比较两个日期大小
bool compare_date(vector<int> date1, vector<int> date2)
{	
	if(date1[0]<date2[0])
		return false;
	if (date1[0] == date2[0] && date1[1] < date2[1])
		return false;
	if (date1[0] == date2[0] && date1[1] == date2[1] && date1[2] < date2[2])
		return false;
	return true;
}

//得到路径的文件名对应的日期
vector<int> get_path_name_date(string file_path)
{
	int i;
	vector<int> file_date;
	for (i = file_path.length(); file_path[i] != '\\'; i--)
	{
		continue;
	}
	file_path = file_path.substr(++i, file_path.length()-8);
	file_date = transform_date(file_path);
	return file_date;
}

//根据-date参数选出应该处理的文件
void pick_log_file(vector<string> file_list)
{
	int i;
	bool process_sign = false;   //用于处理没有设置date参数的情况
	string temp = "";
	if (m_single.find("date") != m_single.end())
		string temp = m_single["-date"];
	else
		process_sign = true;
	vector<int> time = transform_date(temp);
	int left, right;
	for (int i = 0; i < file_list.size(); i++)
	{
		vector<int> file_date = get_path_name_date(file_list[i]);
		if (compare_date(time, file_date)||process_sign)
			process_file(file_list[i]);
		else
			break;
	}
}

//该函数实现了将path路径文件夹下的所有文件读取到files里面
void getFiles(const std::string & path, std::vector<std::string> & files)
{
	//文件句柄  
	long long hFile = 0;
	//文件信息，_finddata_t需要io.h头文件  
	struct _finddata_t fileinfo;
	std::string p;
	int i = 0;
	if ((hFile = _findfirst(p.assign(path).append("\\*").c_str(), &fileinfo)) != -1)
	{
		do
		{
			//如果是目录,迭代之  
			//如果不是,加入列表  
			if ((fileinfo.attrib & _A_SUBDIR))
			{
				//if (strcmp(fileinfo.name, ".") != 0 && strcmp(fileinfo.name, "..") != 0)
				//getFiles(p.assign(path).append("\\").append(fileinfo.name), files);
			}
			else
			{
				files.push_back(p.assign(path).append("\\").append(fileinfo.name));
			}
		} while (_findnext(hFile, &fileinfo) == 0);
		_findclose(hFile);
	}
}

int main(int argc, char* argv[])
{
	process_cmd(argc, argv);

	int i;
	cout << argc;
	for (i = 0; i < argc; i++)
	{
		cout << argv[i] << "\n";      //命令行测试
	}
	vector<string> file_list;
	insert_province("全国");
	getFiles(m_single["-log"], file_list);
	pick_log_file(file_list);
	for (i = 0; i < file_list.size(); i++)
	{
		//cout << file_list[i] << "\n" ;
	}
	system("pause");

}