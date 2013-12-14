CREATE DATABASE MSGBOX CHARACTER SET = UTF8;

SET NAMES 'utf8';

GRANT ALL ON *.* To 'root'@'%';

./mysql --socket /tmp/mysql.sock


mysql -uroot -p

-t: show tcp only
-u: show udp only
-l: show listened port only
-a: show all (listened + established)
-n: show as network ip

netstat -tl | grep mysql
netstat -ta | grep mysql
netstat -tan | grep 3306

my.cnf -> bind-address=0.0.0.0