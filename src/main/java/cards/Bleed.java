package cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;

public class Bleed extends PainCustomCard {

    public static final String ID = "Bleed";

    private static final int COST = 0;
    private static final int SELF_DMG = 5;
    private static final int UPGRADE_SELF_DMG = 3;

    public Bleed() {
        super(ID, COST, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = SELF_DMG;
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            upgradeName();
            upgradeMagicNumber(UPGRADE_SELF_DMG);
            upgradeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bleed();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS)));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(upgraded ? 2 : 1));
    }
}
