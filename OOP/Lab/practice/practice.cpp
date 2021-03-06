// practice.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <cctype>
#include <queue>
#include <map>
#include <set>

template<typename T>
class bigger {
public:
	bool operator()(const T& left, const T& right)
	{
		return left.first.first < right.first.first;
	}
};

auto f()
{
	return 3;
}

std::pair<int, int> ff()
{
	return { 1, 2 };
}

auto fff()
{
	return std::make_pair(1, 2);
}

void partOne()
{
	auto func = [](const std::pair<std::pair<int, int>, int>& p, const std::pair<std::pair<int, int>, int>& q) -> bool { return p.second < q.second; };
	std::priority_queue < std::pair<std::pair<int, int>, int>, std::vector<std::pair<std::pair<int, int>, int>>, decltype(func)> h(func);

	while (true)
	{
		int option;
		std::cin >> option;

		switch (option)
		{
		case 0:
		{

			std::cout << "Insereaza valoare: ";
			int time, pret, termina;
			std::cin >> termina >> time >> pret;
			h.push({ { termina, time }, pret });
		}
		break;
		case 1:
		{

			std::cout << "Am extras: ";
			auto [pa, pret] = h.top();
			auto [termina, time] = pa;
			std::cout << termina << ' ' << time << ' ' << pret << '\n';
			h.pop();
		}
		break;
		}
	}
}

void dictionar(std::string fisier)
{
	std::ifstream input{ fisier };

	if (!input.is_open())
	{
		throw std::runtime_error{ "Fisierul nu a putut fi deschis." };
	}

	std::stringstream ss; ss << input.rdbuf();
	char* mesaj = new char[ss.str().size() + 1];
	strcpy_s(mesaj, ss.str().size() + 1, ss.str().c_str());
	ss.clear(); input.close();

	for (int i = 0; i < strlen(mesaj); i++)
	{
		mesaj[i] = tolower(mesaj[i]);
	}

	char* context = NULL;
	char delims[] = ",.?!\n ";
	char* aux = strtok_s(mesaj, delims, &context);

	std::map<char, std::set<std::string>> h;

	while (aux != NULL)
	{
		h[aux[0]].insert(std::string{ aux });
		aux = strtok_s(aux + strlen(aux) + 1, delims, &context);
	}

	delete[] mesaj;

	for (const auto& m : h)
	{
		std::cout << m.first << " : (" << m.second.size() << " words) ";
		for (const auto& s : m.second)
		{
			std::cout << s << ' ';
		}
		std::cout << std::endl;
	}
}

int main()
{
	// partOne();
	dictionar("cuvinte.txt");

	// Keep window open
	char ch; std::cin >> ch;

	return 0;
}

