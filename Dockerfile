FROM hseeberger/scala-sbt
WORKDIR /schach
ADD . /schach
CMD sbt test
