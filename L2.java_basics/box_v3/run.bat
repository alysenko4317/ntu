cd ..\box_v2
call build.bat
cd ..\box_v3
copy "..\box_v2\target\*.jar" .
javac -cp ".;cost-calc.jar" Program.java
java -cp ".;cost-calc.jar" Program