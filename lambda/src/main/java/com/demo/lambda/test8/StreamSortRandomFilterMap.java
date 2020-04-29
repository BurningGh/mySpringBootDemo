package com.demo.lambda.test8;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lz
 * @date 2019/10/16
 */
public class StreamSortRandomFilterMap {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 创建 Stream的四种方式
     * 1.通过Collection得Stream（）方法（串行流）
     * 或者 parallelStream（）方法（并行流）创建Stream
     */
    public static void test1() {

        //1. 通过Collection得Stream（）方法（串行流）
        //或者 parallelStream（）方法（并行流）创建Stream
        List<String> list = new ArrayList<String>();
        Stream<String> stream1 = list.stream();

        Stream<String> stream2 = list.parallelStream();
        Stream<String> stringStream = stream1.parallel();
    }

    /**
     * 创建 Stream的四种方式
     * 2. 通过Arrays中得静态方法stream（）获取数组流
     */
    public void test2() {

        //2. 通过Arrays中得静态方法stream（）获取数组流
        IntStream stream = Arrays.stream(new int[]{3, 5});

    }

    /**
     * 创建 Stream的四种方式
     * 3. 通过Stream类中得 of（）静态方法获取流
     */
    @Test
    public void test3() {

        //3. 通过Stream类中得 of（）静态方法获取流
        Stream<String> stream = Stream.of("4645", "huinnj");

    }

    /**
     * 创建 Stream的四种方式
     * 4. 创建无限流(迭代、生成)
     */
    @Test
    public void test4() {

        //4. 创建无限流
        //迭代（需要传入一个种子，也就是起始值，然后传入一个一元操作）
        Stream<Integer> stream1 = Stream.iterate(2, (x) -> x * 2);
        //stream1.forEach(System.out::println);

        //生成(无限产生对象)
        Stream<Double> stream2 = Stream.generate(Math::random);
        stream2.forEach(System.out::println);

    }

    List<Person> list = new ArrayList<>();

    @Before
    public void before() {
        list.add(new Person("张三", 18, "男"));
        list.add(new Person("李四", 38, "男"));
        list.add(new Person("王五", 35, "男"));
        list.add(new Person("钱七", 56, "男"));
        list.add(new Person("翠花", 34, "女"));
        list.add(new Person("翠花", 34, "女"));
    }

    /**
     * 筛选与切片
     * filter —— 接收Lambda ，从流中排除某些元素。
     */
    @Test
    public void test5() {
        //内部迭代：在此过程中没有进行过迭代，由Stream api进行迭代
        //中间操作：不会执行任何操作
        Stream<Person> stream = list.stream().filter((e) -> {
            System.out.println("Stream API 中间操作");
            return e.getAge() > 30;
        });

        //终止操作：只有执行终止操作才会执行全部。即：延迟加载
        stream.forEach(System.out::println);
    }

    @Test
    public void test6() {
        //外部迭代
        Iterator<Person> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * limit —— 截断流，使其元素不超过给定数量。
     */
    @Test
    public void test7() {
        //过滤之后取2个值
        list.stream().filter((e) -> e.getAge() > 30).limit(2).forEach(System.out::println);
    }

    /**
     * skip（n）—— 跳过元素，返回一个扔掉了前n个元素的流。
     * 若流中元素不足n个，则返回一个空，与limit（n）互补。
     */
    @Test
    public void test8() {
        //跳过前2个值
        list.stream().skip(2).forEach(System.out::println);
    }

    /**
     * distinct —— 筛选，通过流所生成元素的hashCode（）和equals（）去除重复元素
     * <p>
     * distinct 需要实体中重写hashCode（）和 equals（）方法才可以使用
     */
    @Test
    public void test9() {
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * map —— 映射 ，将元素转换成其他形式或者提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     */
    @Test
    public void test10() {
        //将流中每一个元素都映射到map的函数中，每个元素执行这个函数，再返回
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream().map(String::toUpperCase).forEach(System.out::printf);

        //获取Person中的每一个人得名字name，再返回一个集合
        List<String> names = this.list.stream().map(Person::getName).
                collect(Collectors.toList());

        names.forEach(System.out::println);
    }


    /**
     * flatMap —— 接收一个函数作为参数，将流中的每个值都换成一个流，然后把所有流连接成一个流
     */
    @Test
    public void test11() {
        StreamSortRandomFilterMap s = new StreamSortRandomFilterMap();
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream().flatMap(s::filterCharacter).forEach(System.out::println);

        //如果使用map则需要这样写
        list.stream().map(s::filterCharacter).forEach((e) -> e.forEach(System.out::println));
    }

    /**
     * 将一个字符串转换为流
     *
     * @param str
     * @return
     */
    public Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }


    /**
     * sorted有两种方法，一种是不传任何参数，叫自然排序，还有一种需要传Comparator 接口参数，叫做定制排序。
     */
    @Test
    public void test12() {
        // 自然排序
        List<Person> persons = list.stream().sorted().collect(Collectors.toList());
        persons.forEach(System.out::println);

        //定制排序
        List<Person> persons1 = list.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        persons1.forEach(System.out::println);
    }

    List<Person> persons;

    @Before
    public void beforeList() {
        persons = Arrays.asList(
                new Person("张三", 76, "男", Status.FREE),
                new Person("李四", 12, "女", Status.BUSY),
                new Person("王五", 35, "男", Status.FREE),
                new Person("赵六", 3, "男", Status.FREE),
                new Person("钱七", 56, "男", Status.FREE),
                new Person("翠花", 34, "女", Status.VOCATION),
                new Person("翠花", 34, "女", Status.FREE),
                new Person("翠花", 34, "女", Status.FREE)
        );
    }

    /**
     * allMatch —— 检查是否匹配所有元素。
     * 判断所有状态是否都是FREE
     */
    @Test
    public void test13() {
        boolean b = persons.stream().allMatch((e) -> Status.FREE.equals(e.getStatus()));
        System.out.println(b);
    }


    /**
     * anyMatch —— 检查是否至少匹配所有元素。
     * 判断是否有一个是FREE
     */
    @Test
    public void test14() {
        boolean b = persons.stream().anyMatch((e) -> Status.FREE.equals(e.getStatus()));
        System.out.println(b);
    }


    /**
     * noneMatch —— 检查是否没有匹配所有元素。
     * 判断是否没有FREE
     */
    @Test
    public void test15() {
        boolean b = persons.stream().noneMatch((e) -> Status.FREE.equals(e.getStatus()));
        System.out.println(b);
    }

    /**
     * findFirst —— 返回第一个元素。
     */
    @Test
    public void test16() {
        Optional<Person> person = persons.stream().findFirst();

        person.orElse(new Person("王五", 35, "男", Status.BUSY));
        System.out.println(person);

    }

    /**
     * findAny —— 返回当前流中任意元素。
     */
    @Test
    public void test17() {
        Optional<Person> person = persons.stream().findAny();
        System.out.println(person);

        person.orElse(new Person("王五", 35, "男", Status.BUSY));
    }

    /**
     * count —— 返回流中元素总个数。
     */
    @Test
    public void test18() {
        long count = persons.stream().count();
        System.out.println(count);
    }

    /**
     * max —— 返回流中最大值。
     */
    @Test
    public void test19() {
        Optional<Person> person = persons.stream().max(Comparator.comparingDouble(Person::getAge));
        System.out.println(person);
    }

    /**
     * min —— 返回流中最小值。
     */
    @Test
    public void test20() {
        Optional<Person> person = persons.stream().min((e1, e2) -> Double.compare(e1.getAge(), e2.getAge()));
        System.out.println(person);
    }

    /**
     * reduce（T identitty，BinaryOperator）首先，需要传一个起始值，然后，传入的是一个二元运算。
     */
    @Test
    public void test21() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
    }

    /**
     * reduce（BinaryOperator）此方法相对于上面方法来说，没有起始值，则有可能结果为空，所以返回的值会被封装到Optional中
     */
    @Test
    public void test22() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> sum = list.stream().reduce(Integer::sum);
        System.out.println(sum.get());
    }

    /**
     * Collectors.toList（） 将流转换成List
     */
    @Test
    public void test23() {
        List<String> names = this.list.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(names);
    }


    /**
     * Collectors.toSet（） 将流转换成Set
     */
    @Test
    public void test24() {
        Set<String> names = this.list.stream().map(Person::getName).collect(Collectors.toSet());
        System.out.println(names);
    }


    /**
     * Collectors.toCollection（）将流转换为其他类型的集合
     */
    @Test
    public void test25() {
        LinkedList<String> names = this.list.stream().map(Person::getName).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(names);
    }


    /**
     * Collectors.counting()  总数
     */
    @Test
    public void test26() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Long count = list.stream().collect(Collectors.counting());
        System.out.println(count);
    }

    /**
     * Collectors.averagingInt() 、
     * Collectors.averagingDouble()、
     * Collectors.averagingLong() 平均数，
     * 者三个方法都可以求平均数，不同之处在于传入得参数类型不同，
     * 返回值都为Double
     */
    @Test
    public void test27() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Double avg = list.stream().collect(Collectors.averagingInt((x) -> x));
        System.out.println(avg);
    }

    /**
     * Collectors.summingInt() 、
     * Collectors.summingDouble()、
     * Collectors.summingLong() 求和，
     * 者三个方法都可以求总数，不同之处在于传入得参数类型不同，
     * 返回值为Integer， Double， Long
     */
    @Test
    public void test28() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().collect(Collectors.summingInt((x) -> x));
        System.out.println(sum);
    }

    /**
     * Collectors.maxBy（） 求最大值
     */
    @Test
    public void test29() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> max = list.stream().collect(Collectors.maxBy((x, y) -> Integer.compare(x, y)));
        System.out.println(max.get());
    }

    /**
     * Collectors.minBy（） 求最小值
     */
    @Test
    public void test30() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> min = list.stream().collect(Collectors.minBy((x, y) -> Integer.compare(x, y)));
        System.out.println(min.get());
    }

    /**
     * Collectors.groupingBy（）分组 ，返回一个map
     */
    @Test
    public void test31() {
        Map<String, List<Person>> personMap = list.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println(personMap);
    }

    /**
     * Collectors.groupingBy（）多级分组 ，返回一个map
     */
    @Test
    public void test32() {
        Map<String, Map<Status, List<Person>>> personMap = persons.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getStatus)));
        System.out.println(personMap);
    }

    /**
     * Collectors.partitioningBy（） 分区，参数中传一个函数，返回true，和false 分成两个区
     */
    @Test
    public void test33() {
        Map<Boolean, List<Person>> personMap = list.stream().collect(Collectors.partitioningBy((x) -> x.getAge() > 30));
        System.out.println(personMap);
    }
}

enum Status {
    FREE, BUSY, VOCATION
}

class Person implements Comparable {
    private String name;
    private int age;
    private String sex;

    private Status status;

    public Person(String name, int age, String sex, Status status) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.status = status;
    }

    public Person() {
    }

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name) &&
                sex.equals(person.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Person)
            return Integer.compare(this.getAge(), ((Person) o).getAge());
        return 0;
    }
}
