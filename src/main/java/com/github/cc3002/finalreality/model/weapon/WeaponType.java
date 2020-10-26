package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;

import java.util.Map;
import static java.util.Map.entry;

/**
 * Enumeration of all the weapon types.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @version 1.02
 * @since 1.0
 */
public enum WeaponType {
  SWORD (false,
          Map.ofEntries(
                  entry(CharacterClass.KNIGHT, Boolean.TRUE),
                  entry(CharacterClass.ENGINEER, Boolean.FALSE),
                  entry(CharacterClass.THIEF, Boolean.TRUE),
                  entry(CharacterClass.BLACK_MAGE, Boolean.FALSE),
                  entry(CharacterClass.WHITE_MAGE, Boolean.FALSE))
  ),
  AXE (false,
          Map.ofEntries(
                  entry(CharacterClass.KNIGHT, Boolean.TRUE),
                  entry(CharacterClass.ENGINEER, Boolean.TRUE),
                  entry(CharacterClass.THIEF, Boolean.FALSE),
                  entry(CharacterClass.BLACK_MAGE, Boolean.FALSE),
                  entry(CharacterClass.WHITE_MAGE, Boolean.FALSE))
  ),
  KNIFE (false,
          Map.ofEntries(
                  entry(CharacterClass.KNIGHT, Boolean.TRUE),
                  entry(CharacterClass.ENGINEER, Boolean.FALSE),
                  entry(CharacterClass.THIEF, Boolean.FALSE),
                  entry(CharacterClass.BLACK_MAGE, Boolean.TRUE),
                  entry(CharacterClass.WHITE_MAGE, Boolean.FALSE))
  ),
  STAFF (true,
          Map.ofEntries(
                  entry(CharacterClass.KNIGHT, Boolean.FALSE),
                  entry(CharacterClass.ENGINEER, Boolean.FALSE),
                  entry(CharacterClass.THIEF, Boolean.TRUE),
                  entry(CharacterClass.BLACK_MAGE, Boolean.TRUE),
                  entry(CharacterClass.WHITE_MAGE, Boolean.TRUE))
  ),
  BOW (false,
          Map.ofEntries(
                  entry(CharacterClass.KNIGHT, Boolean.FALSE),
                  entry(CharacterClass.ENGINEER, Boolean.TRUE),
                  entry(CharacterClass.THIEF, Boolean.TRUE),
                  entry(CharacterClass.BLACK_MAGE, Boolean.FALSE),
                  entry(CharacterClass.WHITE_MAGE, Boolean.FALSE))
  );

  /**
   * Indicates if this Weapon can use magical properties: having magicDamage > 0
   * and being able to use spells (on mages only), true if it is, false otherwise
   */
  public final boolean isMagical;

  /**
   * Indicates which Character classes can equip this Weapon
   */
  public final Map<CharacterClass, Boolean> usage;

  /**
   * Incorporates parameters (isMagical and usage) to each WeaponType
   *
   * @param isMagical
   *    'isMagical' parameter
   * @param usage
   *    'usage' parameter
   * @since 1.0
   */
  WeaponType(boolean isMagical, Map<CharacterClass, Boolean> usage){
    this.isMagical = isMagical;
    this.usage = usage;
  }

}
