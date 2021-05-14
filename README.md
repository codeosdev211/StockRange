# Stock Range

Development Environment Used:
- Operating System : Linux (Manjaro)
- Text Editor : Vim
- Language : Java (java - openjdk version  "1.0.0_292", javac - 1.8.0_292)
- .csv file viewed in : libre office calc

Installation: Just download archive or clone this repo on your device

How to run: after installation, run commands given below

go into the source folder with java files
$ cd src

compile java files 
$ javac *.java

compile with package 
$ javac -d . *.java

mention filenames with full path, e.g., /home/Documents/filename.csv
$ java src.StockRange <filepath1> <filepath2> .... <filepathN>

example: 
java src.StockRange ~/Downloads/cm29JAN2020bhav.csv ~/Downloads/cm30JAN2020bhav.csv ~/Downloads/cm31JAN2020bhav.csv

The output of the program will be generated in the same directory of the code.
Ouput File Name: SRange.csv

Note: the output file will not have values sorted alphabet wise but can be 
easily sorted using excel or libre office calc while verifying data manually




