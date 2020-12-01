package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

  private static final String AXE_NAME = "Test Axe";
  private static final String STAFF_NAME = "Test Staff";
  private static final String SWORD_NAME = "Test Sword";
  private static final String BOW_NAME = "Test Bow";
  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int MAGIC_DAMAGE = 20;
  private static final int SPEED = 10;

  private IWeapon testAxe;
  private IWeapon testStaff;
  private IWeapon testSword;
  private IWeapon testBow;
  private IWeapon testKnife;

  @BeforeEach
  void setUp() {
    testAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    testStaff = new Staff(STAFF_NAME, DAMAGE, SPEED, MAGIC_DAMAGE);
    testSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    testBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);
  }

  @Test
  void constructorTest() {
    IWeapon expectedAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    IWeapon expectedStaff = new Staff(STAFF_NAME, DAMAGE, SPEED, MAGIC_DAMAGE);
    IWeapon expectedSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    IWeapon expectedBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    IWeapon expectedKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);

    assertEquals(expectedAxe, testAxe);
    assertEquals(expectedAxe.hashCode(), testAxe.hashCode());
    assertEquals(expectedStaff, testStaff);
    assertEquals(expectedStaff.hashCode(), testStaff.hashCode());
    assertEquals(expectedSword, testSword);
    assertEquals(expectedSword.hashCode(), testSword.hashCode());
    assertEquals(expectedBow, testBow);
    assertEquals(expectedBow.hashCode(), testBow.hashCode());
    assertEquals(expectedKnife, testKnife);
    assertEquals(expectedKnife.hashCode(), testKnife.hashCode());
    assertEquals(MAGIC_DAMAGE, testStaff.getMagicDamage());
    assertEquals(0, testSword.getMagicDamage());
    assertEquals(0, testBow.getMagicDamage());
    assertEquals(0, testKnife.getMagicDamage());
    assertEquals(0, testAxe.getMagicDamage());
  }
}