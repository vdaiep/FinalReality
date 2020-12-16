Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com).
Broadly speaking, for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

Version Notes: v1.04 (Tarea 2)
----------------------------

Each character has a name, class, HP and defense. Mage classes also have mana (set to 0 on other classes). The
classes are the following:

- Knight
- Engineer
- Thief
- White Mage
- Black Mage

On each character's turn (to be implemented on a future release), it can equip a weapon.
The weapons classes are:

- Sword
- Axe
- Knife
- Bow
- Staff

Each weapon has a name, weight, damage, and staffs also have magic damage.

The model is linked to a main Controller class, which handles characters, enemies and weapons, and will 
be a link between the user interaction and the model on future releases.