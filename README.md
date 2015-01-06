jvm-serializer
==============
## Description
### This project is a function and performance benchmark test for kyro3,fastjson and hessian codec(Serialize and Deserialize).which are most popular non-schema Serialize and Deserialize tools nowadays. 
## Environment
### Hardware: 
####  Intel(R) Core(TM) i5-3230M CPU @ 2.60GHz,4 core 8G mem
### Software:
#### kernel 3.13.0-43-generic,x86_64 GNU/Linux
#### Java HotSpot(TM) 64-Bit Server VM 1.7.0_25

## Test case
### 1.After 12000 times warm up op,do 5000 times codec loop.recycle this procedure 10 times.
### 2.Consider some special java type,such as BitEnum,EnumSet etc...
### Report
| Tool          | size        | cost(ms)  |
| ------------- |-------------| ----------|
| Kryo3         |  79         | 79        |
| Fastjson      |  340        | 196       |
| Hessian4      |  718        | 342       |

#### If you have any good advice,please contact me through email:fengjia10@gmail.com