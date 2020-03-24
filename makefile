# 308046937 
# malkama
compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*.java

run:
	java -cp biuoop-1.4.jar:bin ExpressionsTest

bonus:
	java -cp biuoop-1.4.jar:bin SimplificationDemo
    
bin:
	mkdir bin
check:
	java -jar /u/stud/malkama/oop/checkstyle-5.7-all.jar -c /u/stud/malkama/oop/biuoop.xml src/*.java