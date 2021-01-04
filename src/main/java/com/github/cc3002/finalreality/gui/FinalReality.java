package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Main entry point for the application.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla
 *
 * @version 1.05
 * @since 1.05
 */
public class FinalReality extends Application {

    /**
     * Status of the game
     * 0: Welcome screen and game set-up.
     * 1: FIGHT!
     * 2: Main fight screen.
     * 3: Victory screen.
     * 4: Defeat screen.
     * 9: Rules screen
     */
    private int status = 0;

    /**
     * Main controller that interacts with the model.
     */
    private final Controller controller = new Controller();

    /**
     * Chosen names for characters and weapons.
     */
    private final ArrayList<String> chosenKnightsNames = new ArrayList<>();
    private final ArrayList<String> chosenEngineerNames = new ArrayList<>();
    private final ArrayList<String> chosenThiefNames = new ArrayList<>();
    private final ArrayList<String> chosenBlackMageNames = new ArrayList<>();
    private final ArrayList<String> chosenWhiteMageNames = new ArrayList<>();
    private final ArrayList<String> chosenSwordNames = new ArrayList<>();
    private final ArrayList<String> chosenAxeNames = new ArrayList<>();
    private final ArrayList<String> chosenKnifeNames = new ArrayList<>();
    private final ArrayList<String> chosenStaffNames = new ArrayList<>();
    private final ArrayList<String> chosenBowNames = new ArrayList<>();

    /**
     * Count of created and total characters and weapons during set-up.
     */
    Text numberOfCreatedCharacters = new Text();
    Text numberOfCreatedWeapons = new Text();
    private int numberOfCharacters = 0;
    private int numberOfWeapons = 0;
    private final int TOTAL_NUMBER_OF_WEAPONS = 5;
    private final int TOTAL_NUMBER_OF_CHARACTERS = 5;

    /**
     * Characters' stats containers.
     */
    private ArrayList<String> playerName = new ArrayList<>();
    private ArrayList<Integer> playerHP = new ArrayList<>();
    private ArrayList<Integer> playerMaxHP = new ArrayList<>();
    private ArrayList<Integer> playerDefense = new ArrayList<>();
    private ArrayList<String> playerWeapon = new ArrayList<>();
    private ArrayList<Integer> playerAttack = new ArrayList<>();
    private ArrayList<Integer> playerWeight = new ArrayList<>();

    /**
     * Enemies' stats containers.
     */
    private ArrayList<String> enemyName = new ArrayList<>();
    private ArrayList<Integer> enemyHP = new ArrayList<>();
    private ArrayList<Integer> enemyMaxHP = new ArrayList<>();
    private ArrayList<Integer> enemyDefense = new ArrayList<>();
    private ArrayList<Integer> enemyAD = new ArrayList<>();
    private ArrayList<Integer> enemyWeight = new ArrayList<>();

    /**
     * Weapons' stats containers.
     */
    private ArrayList<String> weaponName = new ArrayList<>();
    private ArrayList<Integer> weaponAD = new ArrayList<>();
    private ArrayList<Integer> weaponWeight = new ArrayList<>();
    private ArrayList<String> weaponBearer = new ArrayList<>();

    /**
     * Current player character's turn.
     */
    private int currentPlayer = 0;

    /**
     * Current character, enemy and weapon on display.
     */
    private int currentCharacter = 0;
    private int currentEnemy = 0;
    private int currentWeapon = 0;

    /**
     * Labels shown on fight areas.
     */
    Label characterHPLabel = new Label("HP: ");
    Label enemyHPLabel = new Label("HP: ");
    Label characterAttackLabel = new Label("ATK:");
    Label enemyAttackLabel = new Label("ATK:");
    Label weaponAttackLabel = new Label("ATK:");
    Label characterDefenseLabel = new Label("DEF:");
    Label enemyDefenseLabel = new Label("DEF:");
    Label characterWeightLabel = new Label("WGH:");
    Label enemyWeightLabel = new Label("WGH:");
    Label weaponWeightLabel = new Label("WGH:");
    Label characterWeaponLabel = new Label("Weapon: ");
    Label weaponBearerLabel = new Label("Bearer: ");

    /**
     * Labels with characters', enemies' and weapons' stats.
     */
    Label currentCharacterName = new Label();
    Label currentCharacterHP = new Label();
    Label currentCharacterDefense = new Label();
    Label currentCharacterWeapon = new Label();
    Label currentCharacterAttack = new Label();
    Label currentCharacterWeight = new Label();
    Label currentEnemyName = new Label();
    Label currentEnemyHP = new Label();
    Label currentEnemyDefense = new Label();
    Label currentEnemyAD = new Label();
    Label currentEnemyWeight = new Label();
    Label currentWeaponName = new Label();
    Label currentWeaponAD = new Label();
    Label currentWeaponWeight = new Label();
    Label currentWeaponBearer = new Label();

    /**
     * Buttons.
     */
    Button saveCharacterButton = new Button("SAVE");
    Button saveWeaponButton = new Button("SAVE");
    Button startFightButton = new Button("START");
    Button seeRulesButton = new Button("RULES");
    Button hideRulesButton = new Button("BACK");
    Button previousCharacterButton = new Button("Previous");
    Button nextCharacterButton = new Button("Next");
    Button previousEnemyButton = new Button("Previous");
    Button nextEnemyButton = new Button("Next");
    Button previousWeaponButton = new Button("Previous");
    Button nextWeaponButton = new Button("Next");
    Button equipButton = new Button("Equip");
    Button attackButton = new Button("Attack");

    /**
     * Displayed messages.
     */
    Text welcomeMessage = new Text("Welcome to Final Reality!");
    Text chooseYourCharactersMessage = new Text("Choose 5 characters\n    to fight for you!");
    Text chooseYourWeaponsMessage = new Text("  Choose 5 weapons to\nmassacre your enemies!");
    Text nameErrorMessage = new Text("Names shouldn't be longer\n       than 12 characters!");
    Text fightMessage = new Text("FIGHT!");
    Text victoryMessage = new Text("VICTORY!");
    Text defeatMessage = new Text("Defeat.");

    /**
     * Graphical figures.
     */
    GridPane charactersForm = new GridPane();
    GridPane weaponsForm = new GridPane();
    Path charactersArea = new Path();
    Path enemiesArea = new Path();
    Path logArea = new Path();
    Path inventoryArea = new Path();
    Line characterSeparator1 = new Line();
    Line characterSeparator2 = new Line();
    Line enemiesSeparator1 = new Line();
    Line enemiesSeparator2 = new Line();
    Line inventorySeparator1 = new Line();
    Line inventorySeparator2 = new Line();

    /**
     * Rules of the game.
     */
    Text rules = new Text();

    /**
     * Container of each log displayed during battle.
     */
    LinkedList<Text> log = new LinkedList<>();

    /**
     * Auxiliary launcher
     * @param args
     *    ignored
     * @since 1.05
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Main launcher.
     * @param primaryStage
     *    inherited
     * @since 1.05
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Final Reality");
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setResizable(false);
        setUpMessages(root);
        setUpForms(root);
        setUpBattleAreas(root);
        setUpTimer();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Sets up the text messages shown before and after battle.
     * @param root
     *    main node
     * @since 1.05
     */
    private void setUpMessages(Group root){
        welcomeMessage.setLayoutX(40);
        welcomeMessage.setLayoutY(120);
        welcomeMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 100));
        seeRulesButton.setLayoutX(575);
        seeRulesButton.setLayoutY(700);
        seeRulesButton.setScaleX(2);
        seeRulesButton.setScaleY(2);
        EventHandler<ActionEvent> seeRulesEvent = event -> status = 9;
        seeRulesButton.setOnAction(seeRulesEvent);
        hideRulesButton.setLayoutX(575);
        hideRulesButton.setLayoutY(700);
        hideRulesButton.setScaleX(2);
        hideRulesButton.setScaleY(2);
        EventHandler<ActionEvent> hideRulesEvent = event -> {
            if (numberOfCharacters + numberOfWeapons == TOTAL_NUMBER_OF_CHARACTERS + TOTAL_NUMBER_OF_WEAPONS) {
                status = 1;
            } else {
                status = 0;
            }
        };
        hideRulesButton.setOnAction(hideRulesEvent);
        rules.setLayoutX(100);
        rules.setLayoutY(100);
        rules.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        writeRules(rules);
        fightMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 200));
        fightMessage.setLayoutX(300);
        fightMessage.setLayoutY(300);
        fightMessage.setVisible(false);
        startFightButton.setLayoutX(575);
        startFightButton.setLayoutY(600);
        startFightButton.setScaleX(2);
        startFightButton.setScaleY(2);
        startFightButton.setVisible(false);
        EventHandler<ActionEvent> startFightEvent = event -> status = 2;
        startFightButton.setOnAction(startFightEvent);
        victoryMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 200));
        victoryMessage.setLayoutX(100);
        victoryMessage.setLayoutY(300);
        victoryMessage.setVisible(false);
        defeatMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 200));
        defeatMessage.setLayoutX(300);
        defeatMessage.setLayoutY(300);
        defeatMessage.setVisible(false);
        nameErrorMessage.setLayoutX(500);
        nameErrorMessage.setLayoutY(550);
        nameErrorMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        nameErrorMessage.setVisible(false);
        root.getChildren().add(welcomeMessage);
        root.getChildren().add(rules);
        root.getChildren().add(seeRulesButton);
        root.getChildren().add(hideRulesButton);
        root.getChildren().add(fightMessage);
        root.getChildren().add(startFightButton);
        root.getChildren().add(victoryMessage);
        root.getChildren().add(defeatMessage);
        root.getChildren().add(nameErrorMessage);
    }

    /**
     * Sets up the forms to register characters and weapons at the start of the game.
     * @param root
     *    main node
     * @since 1.05
     */
    private void setUpForms(Group root){
        chooseYourCharactersMessage.setLayoutX(140);
        chooseYourCharactersMessage.setLayoutY(450);
        chooseYourCharactersMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        chooseYourWeaponsMessage.setLayoutX(790);
        chooseYourWeaponsMessage.setLayoutY(450);
        chooseYourWeaponsMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        numberOfCreatedCharacters.setLayoutX(250);
        numberOfCreatedCharacters.setLayoutY(530);
        numberOfCreatedCharacters.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        numberOfCreatedWeapons.setLayoutX(930);
        numberOfCreatedWeapons.setLayoutY(530);
        numberOfCreatedWeapons.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        charactersForm.setHgap(10);
        charactersForm.setVgap(10);
        charactersForm.setLayoutX(150);
        charactersForm.setLayoutY(550);
        Label selectCharacterName = new Label("Select Name: ");
        Label selectCharacterClass = new Label("Select Class: ");
        TextField characterNameField = new TextField();
        ChoiceBox characterClassField = new ChoiceBox();
        characterClassField.getItems().addAll("Knight", "Engineer", "Thief", "Black Mage", "White Mage");
        charactersForm.add(selectCharacterName, 0, 1);
        charactersForm.add(selectCharacterClass, 0, 2);
        charactersForm.add(characterNameField, 1, 1);
        charactersForm.add(characterClassField, 1, 2);
        EventHandler<ActionEvent> saveCharacterEvent = e -> {
            Object chosenClass = characterClassField.getValue();
            if(chosenClass == null){
                return;
            }
            String chosenClassName = chosenClass.toString();
            String chosenName = characterNameField.getText();
            if(chosenName.isBlank() || chosenName.length() > 12){
                nameErrorMessage.setVisible(true);
                return;
            }
            switch (chosenClassName) {
                case "Knight":
                    chosenKnightsNames.add(chosenName);
                    break;
                case "Engineer":
                    chosenEngineerNames.add(chosenName);
                    break;
                case "Thief":
                    chosenThiefNames.add(chosenName);
                    break;
                case "Black Mage":
                    chosenBlackMageNames.add(chosenName);
                    break;
                case "White Mage":
                    chosenWhiteMageNames.add(chosenName);
                    break;
            }
            numberOfCharacters++;
            if(numberOfCharacters == TOTAL_NUMBER_OF_CHARACTERS){
                saveCharacterButton.setVisible(false);
                if(numberOfWeapons == TOTAL_NUMBER_OF_WEAPONS){
                    controller.setUpGame(
                            chosenKnightsNames,
                            chosenEngineerNames,
                            chosenThiefNames,
                            chosenBlackMageNames,
                            chosenWhiteMageNames,
                            chosenSwordNames,
                            chosenAxeNames,
                            chosenKnifeNames,
                            chosenStaffNames,
                            chosenBowNames
                    );
                    status++;
                }
            }
            characterNameField.setText("");
            characterClassField.setValue(null);
            nameErrorMessage.setVisible(false);
        };
        saveCharacterButton.setOnAction(saveCharacterEvent);
        charactersForm.add(saveCharacterButton, 1,3);
        root.getChildren().add(charactersForm);



        /* Weapon selection form */
        weaponsForm.setHgap(10);
        weaponsForm.setVgap(10);
        weaponsForm.setLayoutX(820);
        weaponsForm.setLayoutY(550);
        Label selectWeaponName = new Label("Select Name: ");
        Label selectWeaponClass = new Label("Select Class: ");
        TextField weaponNameField = new TextField();
        ChoiceBox weaponClassField = new ChoiceBox();
        weaponClassField.getItems().addAll("Sword", "Axe", "Knife", "Staff", "Bow");
        weaponsForm.add(selectWeaponName, 0, 1);
        weaponsForm.add(selectWeaponClass, 0, 2);
        weaponsForm.add(weaponNameField, 1, 1);
        weaponsForm.add(weaponClassField, 1, 2);
        EventHandler<ActionEvent> saveWeaponEvent = e -> {
            Object chosenClass = weaponClassField.getValue();
            if(chosenClass == null){
                return;
            }
            String chosenClassName = chosenClass.toString();
            String chosenName = weaponNameField.getText();
            if(chosenName.isBlank() || chosenName.length() > 12){
                nameErrorMessage.setVisible(true);
                return;
            }
            switch (chosenClassName) {
                case "Sword":
                    chosenSwordNames.add(chosenName);
                    break;
                case "Axe":
                    chosenAxeNames.add(chosenName);
                    break;
                case "Knife":
                    chosenKnifeNames.add(chosenName);
                    break;
                case "Staff":
                    chosenStaffNames.add(chosenName);
                    break;
                case "Bow":
                    chosenBowNames.add(chosenName);
                    break;
            }
            numberOfWeapons++;
            if(numberOfWeapons == TOTAL_NUMBER_OF_WEAPONS){
                saveWeaponButton.setVisible(false);
                if(numberOfCharacters == TOTAL_NUMBER_OF_CHARACTERS){
                    controller.setUpGame(
                            chosenKnightsNames,
                            chosenEngineerNames,
                            chosenThiefNames,
                            chosenBlackMageNames,
                            chosenWhiteMageNames,
                            chosenSwordNames,
                            chosenAxeNames,
                            chosenKnifeNames,
                            chosenStaffNames,
                            chosenBowNames
                    );
                    status++;
                }
            }
            weaponNameField.setText("");
            weaponClassField.setValue(null);
            nameErrorMessage.setVisible(false);
        };
        saveWeaponButton.setOnAction(saveWeaponEvent);
        weaponsForm.add(saveWeaponButton, 1,3);
        root.getChildren().add(weaponsForm);

        root.getChildren().add(chooseYourCharactersMessage);
        root.getChildren().add(chooseYourWeaponsMessage);
        root.getChildren().add(numberOfCreatedCharacters);
        root.getChildren().add(numberOfCreatedWeapons);
    }

    /**
     * Sets up the battle areas, displaying all the necessary stats for the battle.
     * @param root
     *    main node
     * @since 1.05
     */
    private void setUpBattleAreas(Group root){
        MoveTo charactersInitialPoint = new MoveTo(100, 450);
        LineTo charactersAreaLine1 = new LineTo(550, 450);
        LineTo charactersAreaLine2 = new LineTo(550, 700);
        LineTo charactersAreaLine3 = new LineTo(100, 700);
        LineTo charactersAreaLine4 = new LineTo(100, 450);
        charactersArea.getElements().add(charactersInitialPoint);
        charactersArea.getElements().addAll(
                charactersAreaLine1,
                charactersAreaLine2,
                charactersAreaLine3,
                charactersAreaLine4
        );
        MoveTo enemiesInitialPoint = new MoveTo(650, 100);
        LineTo enemiesAreaLine1 = new LineTo(1100, 100);
        LineTo enemiesAreaLine2 = new LineTo(1100, 350);
        LineTo enemiesAreaLine3 = new LineTo(650, 350);
        LineTo enemiesAreaLine4 = new LineTo(650, 100);
        enemiesArea.getElements().add(enemiesInitialPoint);
        enemiesArea.getElements().addAll(
                enemiesAreaLine1,
                enemiesAreaLine2,
                enemiesAreaLine3,
                enemiesAreaLine4
        );
        MoveTo inventoryInitialPoint = new MoveTo(650, 450);
        LineTo inventoryLine1 = new LineTo(1100, 450);
        LineTo inventoryLine2 = new LineTo(1100, 700);
        LineTo inventoryLine3 = new LineTo(650, 700);
        LineTo inventoryLine4 = new LineTo(650, 450);
        inventoryArea.getElements().add(inventoryInitialPoint);
        inventoryArea.getElements().addAll(
                inventoryLine1,
                inventoryLine2,
                inventoryLine3,
                inventoryLine4
        );
        MoveTo logInitialPoint = new MoveTo(100, 100);
        LineTo logLine1 = new LineTo(550, 100);
        LineTo logLine2 = new LineTo(550, 350);
        LineTo logLine3 = new LineTo(100, 350);
        LineTo logLine4 = new LineTo(100, 100);
        logArea.getElements().add(logInitialPoint);
        logArea.getElements().addAll(
                logLine1,
                logLine2,
                logLine3,
                logLine4
        );
        characterSeparator1.setStartX(400);
        characterSeparator1.setEndX(550);
        characterSeparator1.setStartY(500);
        characterSeparator1.setEndY(500);
        characterSeparator2.setStartX(400);
        characterSeparator2.setEndX(400);
        characterSeparator2.setStartY(450);
        characterSeparator2.setEndY(700);
        enemiesSeparator1.setStartX(650);
        enemiesSeparator1.setEndX(800);
        enemiesSeparator1.setStartY(150);
        enemiesSeparator1.setEndY(150);
        enemiesSeparator2.setStartX(800);
        enemiesSeparator2.setEndX(800);
        enemiesSeparator2.setStartY(100);
        enemiesSeparator2.setEndY(350);
        inventorySeparator1.setStartX(650);
        inventorySeparator1.setEndX(800);
        inventorySeparator1.setStartY(500);
        inventorySeparator1.setEndY(500);
        inventorySeparator2.setStartX(800);
        inventorySeparator2.setEndX(800);
        inventorySeparator2.setStartY(450);
        inventorySeparator2.setEndY(700);
        root.getChildren().add(charactersArea);
        root.getChildren().add(enemiesArea);
        root.getChildren().add(inventoryArea);
        root.getChildren().add(logArea);
        root.getChildren().add(characterSeparator1);
        root.getChildren().add(characterSeparator2);
        root.getChildren().add(enemiesSeparator1);
        root.getChildren().add(enemiesSeparator2);
        root.getChildren().add(inventorySeparator1);
        root.getChildren().add(inventorySeparator2);
        charactersArea.setVisible(false);
        enemiesArea.setVisible(false);
        inventoryArea.setVisible(false);
        logArea.setVisible(false);
        characterSeparator1.setVisible(false);
        characterSeparator2.setVisible(false);
        enemiesSeparator1.setVisible(false);
        enemiesSeparator2.setVisible(false);
        inventorySeparator1.setVisible(false);
        inventorySeparator2.setVisible(false);

        characterHPLabel.setLayoutX(120);
        characterHPLabel.setLayoutY(470);
        characterHPLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(characterHPLabel);
        characterHPLabel.setVisible(false);

        currentCharacterHP.setLayoutX(220);
        currentCharacterHP.setLayoutY(470);
        currentCharacterHP.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentCharacterHP);
        currentCharacterHP.setVisible(false);

        characterDefenseLabel.setLayoutX(120);
        characterDefenseLabel.setLayoutY(510);
        characterDefenseLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(characterDefenseLabel);
        characterDefenseLabel.setVisible(false);

        currentCharacterDefense.setLayoutX(220);
        currentCharacterDefense.setLayoutY(510);
        currentCharacterDefense.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentCharacterDefense);
        currentCharacterDefense.setVisible(false);

        characterWeaponLabel.setLayoutX(120);
        characterWeaponLabel.setLayoutY(580);
        characterWeaponLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(characterWeaponLabel);
        characterWeaponLabel.setVisible(false);

        currentCharacterWeapon.setLayoutX(220);
        currentCharacterWeapon.setLayoutY(580);
        currentCharacterWeapon.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentCharacterWeapon);
        currentCharacterWeapon.setVisible(false);

        characterAttackLabel.setLayoutX(120);
        characterAttackLabel.setLayoutY(620);
        characterAttackLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(characterAttackLabel);
        characterAttackLabel.setVisible(false);

        currentCharacterAttack.setLayoutX(220);
        currentCharacterAttack.setLayoutY(620);
        currentCharacterAttack.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentCharacterAttack);
        currentCharacterAttack.setVisible(false);

        characterWeightLabel.setLayoutX(120);
        characterWeightLabel.setLayoutY(660);
        characterWeightLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(characterWeightLabel);
        characterWeightLabel.setVisible(false);

        currentCharacterWeight.setLayoutX(220);
        currentCharacterWeight.setLayoutY(660);
        currentCharacterWeight.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentCharacterWeight);
        currentCharacterWeight.setVisible(false);

        enemyHPLabel.setLayoutX(820);
        enemyHPLabel.setLayoutY(120);
        enemyHPLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(enemyHPLabel);
        enemyHPLabel.setVisible(false);

        currentEnemyHP.setLayoutX(920);
        currentEnemyHP.setLayoutY(120);
        currentEnemyHP.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentEnemyHP);
        currentEnemyHP.setVisible(false);

        enemyDefenseLabel.setLayoutX(820);
        enemyDefenseLabel.setLayoutY(160);
        enemyDefenseLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(enemyDefenseLabel);
        enemyDefenseLabel.setVisible(false);

        currentEnemyDefense.setLayoutX(920);
        currentEnemyDefense.setLayoutY(160);
        currentEnemyDefense.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentEnemyDefense);
        currentEnemyDefense.setVisible(false);

        enemyAttackLabel.setLayoutX(820);
        enemyAttackLabel.setLayoutY(200);
        enemyAttackLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(enemyAttackLabel);
        enemyAttackLabel.setVisible(false);

        currentEnemyAD.setLayoutX(920);
        currentEnemyAD.setLayoutY(200);
        currentEnemyAD.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentEnemyAD);
        currentEnemyAD.setVisible(false);

        enemyWeightLabel.setLayoutX(820);
        enemyWeightLabel.setLayoutY(240);
        enemyWeightLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(enemyWeightLabel);
        enemyWeightLabel.setVisible(false);

        currentEnemyWeight.setLayoutX(920);
        currentEnemyWeight.setLayoutY(240);
        currentEnemyWeight.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentEnemyWeight);
        currentEnemyWeight.setVisible(false);

        weaponAttackLabel.setLayoutX(820);
        weaponAttackLabel.setLayoutY(470);
        weaponAttackLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(weaponAttackLabel);
        weaponAttackLabel.setVisible(false);

        currentWeaponAD.setLayoutX(920);
        currentWeaponAD.setLayoutY(470);
        currentWeaponAD.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentWeaponAD);
        currentWeaponAD.setVisible(false);

        weaponWeightLabel.setLayoutX(820);
        weaponWeightLabel.setLayoutY(510);
        weaponWeightLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(weaponWeightLabel);
        weaponWeightLabel.setVisible(false);

        currentWeaponWeight.setLayoutX(920);
        currentWeaponWeight.setLayoutY(510);
        currentWeaponWeight.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentWeaponWeight);
        currentWeaponWeight.setVisible(false);

        weaponBearerLabel.setLayoutX(820);
        weaponBearerLabel.setLayoutY(550);
        weaponBearerLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(weaponBearerLabel);
        weaponBearerLabel.setVisible(false);

        currentWeaponBearer.setLayoutX(920);
        currentWeaponBearer.setLayoutY(550);
        currentWeaponBearer.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentWeaponBearer);
        currentWeaponBearer.setVisible(false);

        currentCharacterName.setLayoutX(410);
        currentCharacterName.setLayoutY(465);
        currentCharacterName.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentCharacterName);
        currentCharacterName.setVisible(false);

        currentWeaponName.setLayoutX(660);
        currentWeaponName.setLayoutY(465);
        currentWeaponName.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentWeaponName);
        currentWeaponName.setVisible(false);

        currentEnemyName.setLayoutX(660);
        currentEnemyName.setLayoutY(115);
        currentEnemyName.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.getChildren().add(currentEnemyName);
        currentEnemyName.setVisible(false);

        previousEnemyButton.setLayoutX(650);
        previousEnemyButton.setLayoutY(360);
        EventHandler<ActionEvent> previousEnemyEvent = event -> {
            currentEnemy--;
            if(currentEnemy == -1){
                currentEnemy = enemyName.size() - 1;
            }
        };
        previousEnemyButton.setOnAction(previousEnemyEvent);
        root.getChildren().add(previousEnemyButton);
        previousEnemyButton.setVisible(false);

        nextEnemyButton.setLayoutX(1060);
        nextEnemyButton.setLayoutY(360);
        EventHandler<ActionEvent> nextEnemyEvent = event -> {
            currentEnemy++;
            if(currentEnemy == enemyName.size()){
                currentEnemy = 0;
            }
        };
        nextEnemyButton.setOnAction(nextEnemyEvent);
        root.getChildren().add(nextEnemyButton);
        nextEnemyButton.setVisible(false);

        previousCharacterButton.setLayoutX(100);
        previousCharacterButton.setLayoutY(710);
        EventHandler<ActionEvent> previousCharacterEvent = event -> {
            currentCharacter--;
            if(currentCharacter == -1){
                currentCharacter = playerName.size() - 1;
            }
        };
        previousCharacterButton.setOnAction(previousCharacterEvent);
        root.getChildren().add(previousCharacterButton);
        previousCharacterButton.setVisible(false);

        nextCharacterButton.setLayoutX(510);
        nextCharacterButton.setLayoutY(710);
        EventHandler<ActionEvent> nextCharacterEvent = event -> {
            currentCharacter++;
            if(currentCharacter == playerName.size()){
                currentCharacter = 0;
            }
        };
        nextCharacterButton.setOnAction(nextCharacterEvent);
        root.getChildren().add(nextCharacterButton);
        nextCharacterButton.setVisible(false);

        previousWeaponButton.setLayoutX(650);
        previousWeaponButton.setLayoutY(710);
        EventHandler<ActionEvent> previousWeaponEvent = event -> {
            currentWeapon--;
            if(currentWeapon == -1){
                currentWeapon = numberOfWeapons - 1;
            }
        };
        previousWeaponButton.setOnAction(previousWeaponEvent);
        root.getChildren().add(previousWeaponButton);
        previousWeaponButton.setVisible(false);

        nextWeaponButton.setLayoutX(1060);
        nextWeaponButton.setLayoutY(710);
        EventHandler<ActionEvent> nextWeaponEvent = event -> {
            currentWeapon++;
            if(currentWeapon == numberOfWeapons){
                currentWeapon = 0;
            }
        };
        nextWeaponButton.setOnAction(nextWeaponEvent);
        root.getChildren().add(nextWeaponButton);
        nextWeaponButton.setVisible(false);

        attackButton.setLayoutX(920);
        attackButton.setLayoutY(300);
        attackButton.setScaleX(2);
        attackButton.setScaleY(2);
        EventHandler<ActionEvent> attackEvent = event -> {
            if(controller.getCharacters().get(currentPlayer).getEquippedWeapon() == null){
                updateLog(playerName.get(currentPlayer) + " has no equipped weapon!");
            }
            else{
                if(controller.getCurrentPhase().isEquipmentPhase()){
                    controller.setAttackPhase(currentPlayer);
                }
                controller.getCurrentPhase().setIndexEnemy(currentEnemy);
                controller.getCurrentPhase().doPhase();
            }
        };
        attackButton.setOnAction(attackEvent);
        root.getChildren().add(attackButton);
        attackButton.setVisible(false);

        equipButton.setLayoutX(920);
        equipButton.setLayoutY(650);
        equipButton.setScaleX(2);
        equipButton.setScaleY(2);
        EventHandler<ActionEvent> equipEvent = event -> {
            if (!controller.getCurrentPhase().isEquipmentPhase()) {
                updateLog("You already equipped a weapon on this turn!");
            }
            else{
                if(controller.getCharacters().get(currentPlayer).canEquip(controller.getInventory().get(currentWeapon))){
                    if(weaponBearer.get(currentWeapon).equals(playerName.get(currentPlayer))){
                        updateLog(playerName.get(currentPlayer) + " has already equipped this weapon!");
                    }
                    else {
                        if(!weaponBearer.get(currentWeapon).equals("None")){
                            controller.unequip(playerName.indexOf(weaponBearer.get(currentWeapon)));
                        }
                        controller.getCurrentPhase().setIndexWeapon(currentWeapon);
                        controller.getCurrentPhase().doPhase();
                        updateLog(playerName.get(currentPlayer) + " has equipped " + weaponName.get(currentWeapon) + "!");
                    }
                }
                else{
                    updateLog(playerName.get(currentPlayer) + " can't equip " + weaponName.get(currentWeapon) + "!");
                }
            }
        };
        equipButton.setOnAction(equipEvent);
        root.getChildren().add(equipButton);
        equipButton.setVisible(false);

        for(int i=0; i<15; i++){
            log.add(new Text(""));
        }
        log.add(new Text("A legendary battle has started!"));

        for(int i=0; i<16; i++){
            Text thisText = log.get(i);
            thisText.setLayoutX(120);
            thisText.setLayoutY(120 + i*15);
            thisText.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
            root.getChildren().add(thisText);
            thisText.setVisible(false);
        }
    }

    /**
     * Main animation timer, updates information on each run.
     *
     * @since 1.05
     */
    private void setUpTimer(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(controller.getGameStatus() == -1){
                    attackButton.setVisible(false);
                    equipButton.setVisible(false);
                    setFightAreaVisibility(false);
                    defeatMessage.setVisible(true);
                    status = 4;
                }
                if(controller.getGameStatus() == 1){
                    attackButton.setVisible(false);
                    equipButton.setVisible(false);
                    setFightAreaVisibility(false);
                    victoryMessage.setVisible(true);
                    status = 5;
                }
                if(status == 0){
                    setSetUpAreaVisibility(true);
                    seeRulesButton.setVisible(true);
                    rules.setVisible(false);
                    hideRulesButton.setVisible(false);
                    numberOfCreatedCharacters.setText(numberOfCharacters +"/5");
                    numberOfCreatedWeapons.setText(numberOfWeapons +"/5");
                }
                else if(status == 1){
                    setSetUpAreaVisibility(false);
                    fightMessage.setVisible(true);
                    startFightButton.setVisible(true);
                    seeRulesButton.setVisible(true);
                    hideRulesButton.setVisible(false);
                    rules.setVisible(false);
                    nameErrorMessage.setVisible(false);
                }
                else if(status == 2){
                    setFightAreaVisibility(true);
                    fightMessage.setVisible(false);
                    startFightButton.setVisible(false);
                    seeRulesButton.setVisible(false);
                    currentPlayer = controller.getCurrentPlayer();
                    playerHP = controller.getCharacterHP();
                    playerName = controller.getCharacterNames();
                    playerMaxHP = controller.getCharacterMaxHP();
                    playerDefense = controller.getCharacterDefense();
                    playerWeapon = controller.getCharacterWeapons();
                    playerAttack = controller.getCharacterAttack();
                    playerWeight = controller.getCharacterWeight();
                    enemyName = controller.getEnemyNames();
                    enemyHP = controller.getEnemyHP();
                    enemyMaxHP = controller.getEnemyMaxHP();
                    enemyDefense = controller.getEnemyDefense();
                    enemyAD = controller.getEnemyAD();
                    enemyWeight = controller.getEnemyWeight();
                    weaponName = controller.getWeaponNames();
                    weaponAD = controller.getWeaponAD();
                    weaponWeight = controller.getWeaponWeight();
                    weaponWeight = controller.getWeaponWeight();
                    weaponBearer = controller.getWeaponBearers();
                    try {
                        currentCharacterName.setText(playerName.get(currentCharacter));
                    }
                    catch(IndexOutOfBoundsException i){
                        currentCharacter = 0;
                        currentCharacterName.setText(playerName.get(currentCharacter));
                    }
                    currentCharacterHP.setText(
                            playerHP.get(currentCharacter).toString() + " / " + playerMaxHP.get(currentCharacter).toString()
                    );
                    currentCharacterDefense.setText(playerDefense.get(currentCharacter).toString());
                    currentCharacterWeapon.setText(playerWeapon.get(currentCharacter));
                    currentCharacterAttack.setText(playerAttack.get(currentCharacter).toString());
                    currentCharacterWeight.setText(playerWeight.get(currentCharacter).toString());
                    currentEnemyName.setText(enemyName.get(currentEnemy));
                    currentEnemyHP.setText(
                            enemyHP.get(currentEnemy).toString() + " / " + enemyMaxHP.get(currentEnemy).toString()
                    );
                    currentEnemyDefense.setText(enemyDefense.get(currentEnemy).toString());
                    currentEnemyAD.setText(enemyAD.get(currentEnemy).toString());
                    currentEnemyWeight.setText(enemyWeight.get(currentEnemy).toString());
                    currentWeaponName.setText(weaponName.get(currentWeapon));
                    currentWeaponAD.setText(weaponAD.get(currentWeapon).toString());
                    currentWeaponWeight.setText(weaponWeight.get(currentWeapon).toString());
                    currentWeaponBearer.setText(weaponBearer.get(currentWeapon));

                    if(currentPlayer == currentCharacter) {
                        attackButton.setVisible(true);
                        equipButton.setVisible(true);
                    }
                    else{
                        attackButton.setVisible(false);
                        equipButton.setVisible(false);
                    }
                    for(String text : controller.getToLog()){
                        updateLog(text);
                    }
                    controller.cleanLog();
                }
                else if(status == 9){
                    setSetUpAreaVisibility(false);
                    seeRulesButton.setVisible(false);
                    fightMessage.setVisible(false);
                    startFightButton.setVisible(false);
                    rules.setVisible(true);
                    hideRulesButton.setVisible(true);
                }
            }
        };
        timer.start();
    }

    /**
     * Writes the rules of the game.
     * @param daRules
     *   the rules
     * @since 1.05
     */
    private void writeRules(Text daRules){
        daRules.setText(
                "Choose 5 characters and 5 weapons to fight against 5 CPU-controlled enemies!\n\n" +
                "On each character's turn, you'll choose a weapon to equip and an enemy to attack.\n" +
                "Each character can equip weapons according to its class and the weapon class.\n\n" +
                " - Knights can equip Swords, Axes and Knives.\n" +
                " - Engineers can equip Axes and Bows.\n" +
                " - Thieves can equip Swords, Staffs and Bows.\n" +
                " - Black Mages can equip Knives and Staffs.\n" +
                " - White Mages can equip only Staffs.\n\n" +
                "Characters and enemies have HP and defense. Weapons have attack damage and weight.\n" +
                "The order of the turns is decided according to the weight.\n" +
                "Each class will have different stats!\n" +
                "Enemies can't equip weapons, they have their own fixed attack damage and weight.\n\n" +
                "Damage is calculated as the attack damage of the attacker, minus the defense of the victim.\n" +
                "If a character or enemy goes down to 0 HP, it is out of combat.\n" +
                "The team (characters or enemies) that has all its characters out of combat will lose the game.\n\n" +
                "Choose your team wisely! Good luck!"
        );
    }

    /**
     * Updates the log of the battle.
     * @param data
     *    text contained in the log
     * @since 1.05
     */
    private void updateLog(String data){
        for(int j=0; j<16; j++){
            if(j != 15) {
                log.get(j).setText(log.get(j + 1).getText());
            }
            else{
                log.get(j).setText(data);
            }
        }
    }

    /**
     * Gathers common visibility changes during set up.
     * @param status
     *    true or false
     * @since 1.05
     */
    private void setSetUpAreaVisibility(boolean status){
        welcomeMessage.setVisible(status);
        chooseYourCharactersMessage.setVisible(status);
        chooseYourWeaponsMessage.setVisible(status);
        charactersForm.setVisible(status);
        weaponsForm.setVisible(status);
        numberOfCreatedCharacters.setVisible(status);
        numberOfCreatedWeapons.setVisible(status);
    }

    /**
     * Gathers common visibility changes during the battle.
     * @param status
     *    true or false
     * @since 1.05
     */
    private void setFightAreaVisibility(boolean status){
        charactersArea.setVisible(status);
        enemiesArea.setVisible(status);
        inventoryArea.setVisible(status);
        logArea.setVisible(status);
        characterSeparator1.setVisible(status);
        characterSeparator2.setVisible(status);
        enemiesSeparator1.setVisible(status);
        enemiesSeparator2.setVisible(status);
        inventorySeparator1.setVisible(status);
        inventorySeparator2.setVisible(status);
        characterHPLabel.setVisible(status);
        characterAttackLabel.setVisible(status);
        characterDefenseLabel.setVisible(status);
        characterWeightLabel.setVisible(status);
        characterWeaponLabel.setVisible(status);
        enemyHPLabel.setVisible(status);
        enemyDefenseLabel.setVisible(status);
        enemyAttackLabel.setVisible(status);
        enemyWeightLabel.setVisible(status);
        weaponAttackLabel.setVisible(status);
        weaponWeightLabel.setVisible(status);
        weaponBearerLabel.setVisible(status);
        previousCharacterButton.setVisible(status);
        nextCharacterButton.setVisible(status);
        previousEnemyButton.setVisible(status);
        nextEnemyButton.setVisible(status);
        previousWeaponButton.setVisible(status);
        nextWeaponButton.setVisible(status);
        currentCharacterHP.setVisible(status);
        currentCharacterDefense.setVisible(status);
        currentCharacterWeapon.setVisible(status);
        currentCharacterAttack.setVisible(status);
        currentCharacterWeight.setVisible(status);
        currentEnemyHP.setVisible(status);
        currentEnemyDefense.setVisible(status);
        currentEnemyAD.setVisible(status);
        currentEnemyWeight.setVisible(status);
        currentCharacterName.setVisible(status);
        currentWeaponAD.setVisible(status);
        currentWeaponWeight.setVisible(status);
        currentWeaponBearer.setVisible(status);
        currentWeaponName.setVisible(status);
        currentEnemyName.setVisible(status);
        for(int i=0; i<16; i++){
            log.get(i).setVisible(status);
        }
    }
}