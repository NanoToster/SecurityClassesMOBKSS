package ru.vsu.domains;

//TODO Delete this shit
public class PersonalSecurityGroup {
    private int[] arraySootvetstviya;

    public PersonalSecurityGroup() {
        arraySootvetstviya = new int[9];
    }

    void checkRule(int ruleId) {
        for (int i = 0; i < arraySootvetstviya.length; i++) {
            // делаем запрос в БД,
            // если это правило нужно, и оно true
//                arraySootvetstviya[i]++;
        }
    }
}
