# Process Planner

Used to take in text files that define the process and dates process will be run. Constructs an Excel workbook that shows the suites in a stacked format.

### To Do

1. Let dates have process titles (what is that dates process making)
1. ~~Ignore blank lines in files~~
1. Make jar a runnable file
    1. Double click jar to run
    1. Errors need to be spit out into a txt file
1. Find out why Process Def file can't be read in Windows with CR/LF
1. Speed up the Excel file gen
1. Take year as an argument

### Build

gradle clean cleanPackageSpace shadowJar packageIntoZip
