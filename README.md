Point of Sale System
====================

*Tom Ryan tommy@hypersynergy.net*

NOTE: I am not associated with http://fancypants.com/ in any way, it was just a random name I chose unfortunately!

Current Build Status
--------------

[![Build Status](https://secure.travis-ci.org/tommunist/PointOfSale.png)](http://travis-ci.org/tommunist/PointOfSale)

Runs ant clean run-tests each time a change is pushed using Travis CI.

Running
-------

To Run all tests using ant:
    *ant run-tests*


The main top level test is *PointOfSaleTerminalIntegrationTest*. This covers all the test cases in the problem specification.

JUnit Test report can be found under:
    *build/test-report/html/index.html*

Assumptions
-----------

* Price is not negative
* Only one product code per product
