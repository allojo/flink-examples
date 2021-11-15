package com.example.demo;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import static java.lang.Math.random;

public class BootFlinkApplication {


    @PostConstruct
    public void init() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        ArrayList<SiriEntry> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            double rand = (random() * 10) + 0;
            SiriEntry siriEntry = new SiriEntry(String.valueOf(i), String.valueOf(Math.round(rand)), Timestamp.from(Instant.now()));
            arrayList.add(siriEntry);
        }

        //.sum(1)//.returns(Types.INT)
        SingleOutputStreamOperator<Tuple2<String, Long>> dataStream = env.fromCollection(arrayList).map(siriEntry -> {
                    System.out.println("siriEntry:topic-->" + siriEntry.getTopic());
                    return new Tuple2(siriEntry.getTopic(), 0L);
//                    return siriEntry.getTopic();
                })
                .keyBy(t -> t).window(TumblingProcessingTimeWindows.of(Time.milliseconds(100)))
                .aggregate(new AverageAggregate2());

        System.out.println("_____________________________________________________");
        dataStream.print();
        System.out.println("_____________________________________________________");
        try {
            env.execute();
        } catch (Exception e) {
            System.out.println("Error executing flink job: " + e.getMessage());
        }
    }

    private class AverageAggregate2 implements AggregateFunction<
            Tuple2,
            Long,
            Tuple2<String, Long>
            > {
        @Override
        public Long createAccumulator() {
            return null;
        }

        @Override
        public Long add(Tuple2 tuple2, Long aLong) {
            return null;
        }

        @Override
        public Tuple2<String, Long> getResult(Long aLong) {
            return null;
        }

        @Override
        public Long merge(Long aLong, Long acc1) {
            return null;
        }

    }

//    private class AverageAggregate2 implements AggregateFunction<String, Tuple2<Long, Long>, Double> {
//        public AverageAggregate2() {
//        }
//
//        @Override
//        public Tuple2<Long, Long> createAccumulator() {
//
//            return new Tuple2<Long, Long>(0L, 0L);
//        }
//
//        @Override
//        public Tuple2<Long, Long> add(String s, Tuple2<Long, Long> stringLongTuple2) {
//            System.out.println("Inside Accumulator add:"+stringLongTuple2.f0 + " return:" + (stringLongTuple2.f1 + 1L));
//            return new Tuple2<>(stringLongTuple2.f0, stringLongTuple2.f1 + 1L);        }
//
//        @Override
//        public Double getResult(Tuple2<Long, Long> stringLongTuple2) {
//
//            return stringLongTuple2.f1.doubleValue();
//        }
//
//        @Override
//        public Tuple2<Long, Long> merge(Tuple2<Long, Long> stringLongTuple2, Tuple2<Long, Long> acc1) {
//            return new Tuple2<>(stringLongTuple2.f0, acc1.f1);
//        }
//    }




}
