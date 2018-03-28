package ru.job4j.departments;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * BankAccounts.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.28
 */
public class Departments {
    public Set<String> departments = new TreeSet<>();

    /**
     * Добавляем департамент
     *
     * @param department департамент
     */
    public void addDepartment(String department) {
        departments.add(department);
        while (department.lastIndexOf("\\") != -1 && !departments.contains(department.substring(0, department.lastIndexOf("\\")))) {
            department = department.substring(0, department.lastIndexOf("\\"));
            departments.add(department);
        }
    }

    /**
     * Добавляем список департаментов
     *
     * @param department департамент
     */
    public void addAllDepartment(String[] department) {
        for (String dept : department) {
            addDepartment(dept);
        }
    }


    /**
     * Возвращаем список департаментов по возрастанию
     *
     * @return список департаментов
     */
    public String[] getAsc() {
        String[] result = new String[departments.size()];
        int index = 0;
        for (String dept : departments) {
            result[index] = dept;
            index++;
        }
        return result;
    }

    /**
     * Возвращаем список департаментов по убыванию
     *
     * @return список департаментов
     */
    public String[] getDesc() {
        String[] result = new String[departments.size()];
        TreeSet<String> res = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                char[] firstWord = first.toCharArray();
                char[] secondWord = second.toCharArray();
                int res = 0;
                for (int index = 0; index < Math.min(firstWord.length, secondWord.length); index++) {
                    if (firstWord[index] < secondWord[index]) {
                        res = 1;
                        break;
                    } else if (firstWord[index] > secondWord[index]) {
                        res = -1;
                        break;
                    }
                }
                return res == 0 ? ((Integer) firstWord.length).compareTo(secondWord.length) : res;
            }
        });
        res.addAll(departments);
        int index = 0;
        for (String dept : res) {
            result[index] = dept;
            index++;
        }
        return result;
    }

    /**
     * Сортируем полученный список по убыванию
     *
     * @param input список департаментов
     * @return список
     */
    public String[] getDesc(String[] input) {
        addAllDepartment(input);
        return getDesc();
    }

    /**
     * Сортируем полученный список по возрастанию
     *
     * @param input список департаментов
     * @return список
     */
    public String[] getAsc(String[] input) {
        addAllDepartment(input);
        return getAsc();
    }
}
