package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300, 300};
    public static int[] heroesDamage = {10, 20, 5};
    public static String[] heroesAttackType = {"Phisical", "Magic", "Mental", "Medical"};
    public static int heal = 25;

    public static void medicHeal() {
        Random random = new Random();
        int heroIndex = random.nextInt(heroesDamage.length);
        if (heroesHealth[3] > 0 && heroesHealth[heroIndex] < 100 && heroesHealth[heroIndex] > 0) {
        heroesHealth[heroIndex] += heal;
            System.out.println("Medic healed " + heroesAttackType[heroIndex]);
        }

    }
    public static void main(String[] args) {
        while (!isFinished()) {
            round();
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) { //это часть будет означать,что жизнь героев не могут иметь
                    // минусовое значение
                    heroesHealth[i] = 0; //он сразу приранивает жизнь к нулю
                } else heroesHealth[i] = heroesHealth[i] - bossDamage;// иначе атаки будут продолжаться
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else bossHealth = bossHealth - heroesDamage[i];
            }
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss defence type " + heroesAttackType[randomIndex]);
    }

    //этот метод показывает конец раунда
    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("heroes won!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[3] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("boss won!");
            return true;
        }
        return false;
    }
    //создаем метод ,означающий что происходит во время раунда
    public static void round() {
        printStatics();  //сначала показывает статистику в начале
        System.out.println("round starts!");
        changeBossDefence();  //рандомная защита босса,в зависимости от атаки
        heroesHit();  //бьют герои
        bossHit(); //бьет босс
        medicHeal();
        printStatics();  //этот принт показывает статистику после ударов!
    }
    
    public static void printStatics() {
        System.out.println("-----------");
        System.out.println("Boss health : " + bossHealth);
        System.out.println("warrior health : " + heroesHealth[0]);
        System.out.println("magic health : " + heroesHealth[1]);
        System.out.println("kinetic health : " + heroesHealth[2]);
        System.out.println("medic health : " + heroesHealth[3]);
        System.out.println("-----------");
    }

}
