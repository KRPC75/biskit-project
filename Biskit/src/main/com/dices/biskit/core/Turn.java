package com.dices.biskit.core;

import com.dices.biskit.enums.RuleType;
import com.dices.biskit.enums.SpecialEffect;
import com.dices.biskit.models.Player;
import com.dices.biskit.models.Result;
import com.dices.biskit.models.dices.Dice;
import com.dices.biskit.models.rules.DiceRule;
import com.dices.biskit.models.rules.DoubleRule;
import com.dices.biskit.models.rules.Rule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * Classe gérant le tour d'un joueur
 *
 */
@Component
public class Turn {

    /**
     * Exécution du tour
     *
     * @param player        Joueur devant jouer
     * @param gameDices     Liste des dés de la partie
     * @param gameRules     Liste des règles de la partie
     * @param playerList    Liste des joueurs de la partie
     */
    public void doTurn(Player player, List<Dice> gameDices, List<Rule> gameRules, List<Player> playerList) {
        Result result = throwDices(gameDices);
        List<Rule> rulesToApply = defineRulesToApply(result, player, gameRules);
        applyRules(rulesToApply, gameRules, player, playerList);
    }

    /**
     * Lancement des dés
     *
     * @param diceList  Liste des dés à jeter
     * @return          Le Résultat du lancer de dé
     */
    Result throwDices(List<Dice> diceList) {
        Result result = new Result(diceList);
        result.throwDices();
        return result;
    }

    /**
     * Définition des règles à appliquer sur le résultat du lancé de dé
     *
     * @param result    Résultat du lancé de dé
     * @param player    Joueur ayant jeté les dés
     * @param ruleList  Liste des règles du jeu
     * @return          Liste des règles à appliquer pour le lancé de dé
     */
    List<Rule> defineRulesToApply(Result result, Player player, List<Rule> ruleList) {

        List<Rule> rulesToApply = new ArrayList<>();

        for (Rule rule : ruleList) {
            Boolean applyIt = false;
            switch (rule.getType()) {
                case DICE:
                    applyIt = rule.doApplyIt(result.getDiceList());
                    break;
                case VALUE:
                    applyIt = rule.doApplyIt(result.getTotal());
                    break;
                case PLAYER:
                    applyIt = rule.doApplyIt(player);
                    break;
                case DOUBLE:
                    applyIt = rule.doApplyIt(result.getDiceList());
                default:
                    break;
            }
            if (applyIt)
                rulesToApply.add(rule);
        }
        return rulesToApply;
    }

    /**
     * Application des règles associées au lancé
     *
     * @param rulesToApply  Liste des règles à appliquer
     * @param rules         Liste des règles
     * @param player        Joueur ayant lancé les dés
     * @param playerList    Liste des joueurs
     */
    void applyRules(List<Rule> rulesToApply, List<Rule> rules, Player player, List<Player> playerList) {
        List<SpecialEffect> specialEffects = new ArrayList<>();
        for (Rule rule : rulesToApply) {
            switch (rule.getSpecialEffect()) {
                case JAIL:
                    manageJail(rule, player, rules, playerList);
                    break;
                case DUEL:
                    break;
                case REVERT:
                    break;
                case NEW:
                    break;
                case PASS:
                    break;
                default:
                    break;
            }
            rule.applyIt();
        }
    }

    /**
     * Gestion des prisons
     *
     * @param rule          Règle de prison à créer
     * @param player        Joueur entrant en prison
     * @param ruleList      Liste des règles de la partie
     * @param playerList    Liste des joueurs de la partie
     */
    void manageJail(Rule rule, Player player, List<Rule> ruleList, List<Player> playerList) {
        DoubleRule doubleRule = (DoubleRule) rule;

        // Récupération du potentiel joueur en prison
        Player jailedPlayer = playerList.stream()
                .filter(jPlayer -> jPlayer.hasJail(doubleRule.getDoubleValue()))
                .findFirst().orElse(null);


        if (null != jailedPlayer) {
            jailedPlayer.removeJail(doubleRule.getDoubleValue());
            // Suppression de la règle de dé représentant la prison
            Predicate<Rule> predicate = toEraseRule -> toEraseRule.getType() == RuleType.DICE &&
                    rule.getSpecialEffect() == SpecialEffect.DRINK &&
                    ((DiceRule) rule).getDiceValue() == doubleRule.getDoubleValue();
            ruleList.removeIf(predicate);
        }

        player.addJail(doubleRule.getDoubleValue());
        ruleList.add(makeJail(doubleRule.getDoubleValue()));
    }

    DiceRule makeJail(Integer digit) {
        return new DiceRule(digit, SpecialEffect.DRINK, String.format("Prison de %d Le joueur doit boire une gorgée", digit));
    }

    Rule makeNewRule(RuleType ruleType) {

        switch (ruleType) {
            case DICE:
                break;
            case VALUE:
                break;
            case PLAYER:
                break;
            case SITUATION:
                break;
            case DOUBLE:
                break;
            default:
                break;
        }
        return null;
    }

}
