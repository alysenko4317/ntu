cd step_1
javac CostCalculator.java
cd ..\step_2
copy "..\step_1\*.class" .
javac Program.java
java Program
cd ..
