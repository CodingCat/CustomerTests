package spark

import scala.collection.mutable.ListBuffer

import org.apache.spark.SparkContext

object RandomLettersGenerator {
  def main(args: Array[String]): Unit ={
    val sc = new SparkContext()
    val partitions = args(0).toInt
    val partitionSize = args(1).toInt
    sc.parallelize((0 until partitions).toList, partitions).mapPartitions {
      _ =>
        val list = new ListBuffer[Int]
        for (i <- 0 until partitionSize) {
          list += i
        }
        list.iterator
    }.foreach(x => Unit)
  }
}
