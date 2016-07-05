
# play-time

[![Build Status](https://travis-ci.org/hmrc/play-time.svg?branch=master)](https://travis-ci.org/hmrc/play-time) [ ![Download](https://api.bintray.com/packages/hmrc/releases/play-time/images/download.svg) ](https://bintray.com/hmrc/releases/play-time/_latestVersion)

Sometimes you need to have your service pretend it is some date the future in order to test certain behaviour.  
This little library allows you to do just that.

Currently supports fake dates.
More specific fake dateTime to be added.


resolvers += Resolver.bintrayRepo("hmrc", "releases")
libraryDependencies += "uk.gov.hmrc" %% "play-time" % "[INSERT-VERSION]"
  
## Set Date Using System Property 
You can pass in the Java system property to set the date to whatever you want for local testing purposes as follows

`-Dfeature.fakeDate=yyyy-MM-dd`

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
    
