package kotlin_introduce;

/**
 * Kotlin 与 Java 互相调用
 */
public class Student {

    private int age;
    private int grade;
    private String name = "Lucy";

    /**
     * Kotlin中 getter 和 setter 可以直接定义在变量上
     *
     * @return
     */
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Kotlin中 getter 和 setter 可以直接定义在变量上
     *
     * @return
     */
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String descript() {
        return "name=" + name + ", age=" + age + "，grade=" + grade;
    }

    public void study() {
        //Java lambda表达式
        Study study = () -> {
            System.out.println(name + " study");
        };
        study.study();
    }

    /**
     * Java 调用 Kotlin 接口
     *
     * @param one
     * @param two
     */
    public void add(int one, int two) {
        Add add = (one1, two1) -> {
            System.out.println("求两数之和");
            return one1 + two1;
        };
        add.add(one, two);
    }

    /**
     * 使用Kotlin类
     *
     * @return
     */
    public Teacher getMyTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("Pony");
        teacher.setAge(50);
        return teacher;
    }

}
