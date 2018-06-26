package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import enums.AbstractCardEnum;
import initliziers.ThePainInitializer;

public class SpikedShield extends PainCustomCard {

    public static final String ID = "SpikedShield";

    private static final int COST = 1;
    private static final int BLOCK_AMT = 10;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int SELF_DMG_AMT = 2;
    private static final int UPGRADE_SELF_DMG_ATM = 2;

    public SpikedShield() {
        super(ID, COST, CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.block = this.baseBlock = BLOCK_AMT;
        this.magicNumber = this.baseMagicNumber = SELF_DMG_AMT;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_SELF_DMG_ATM);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpikedShield();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractPlayer, new DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS)));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
    }
}
