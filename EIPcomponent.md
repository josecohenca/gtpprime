# Introduction #

There are EIP implementation in place for Java. For further development it would be good to have the GTP' producer/consumer (client/server) provided as Apache Camel module

# Details #

1. There should be two possibilities provided - to use it as consumer ('from') and as producer ('to')
2. The transaction handling shall be provided:
- in case of failure appropriate response is sent back
- in case of success the appropriate response is sent back
3. The counters shall be provided
4. Error information shall be provided