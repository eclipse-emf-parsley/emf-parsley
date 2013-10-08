package org.eclipse.emf.parsley.dsl.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.parsley.dsl.services.EmfParsleyDslGrammarAccess;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class EmfParsleyDslSyntacticSequencer extends AbstractSyntacticSequencer {

	protected EmfParsleyDslGrammarAccess grammarAccess;
	protected AbstractElementAlias match_FeaturesProvider___FeaturesKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q;
	protected AbstractElementAlias match_FormControlFactory___ControlKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q;
	protected AbstractElementAlias match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a__a;
	protected AbstractElementAlias match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a;
	protected AbstractElementAlias match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___or_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a_RightCurlyBracketKeyword_3_1_3__q___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a__a__;
	protected AbstractElementAlias match_LabelProvider___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a;
	protected AbstractElementAlias match_LabelProvider___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a;
	protected AbstractElementAlias match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a__a;
	protected AbstractElementAlias match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a;
	protected AbstractElementAlias match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___or_____TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a_RightCurlyBracketKeyword_3_0_3__q___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a__a__;
	protected AbstractElementAlias match_LabelProvider_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___or___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3____a;
	protected AbstractElementAlias match_Module___PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1_RightCurlyBracketKeyword_4_6_3__a;
	protected AbstractElementAlias match_Module___RightCurlyBracketKeyword_4_6_3_PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1__a;
	protected AbstractElementAlias match_PropertyDescriptionProvider___TextKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q;
	protected AbstractElementAlias match_ViewerContentProvider___ChildrenKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q;
	protected AbstractElementAlias match_XBlockExpression_SemicolonKeyword_2_1_q;
	protected AbstractElementAlias match_XConstructorCall___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q;
	protected AbstractElementAlias match_XExpressionInClosure_SemicolonKeyword_1_1_q;
	protected AbstractElementAlias match_XFunctionTypeRef___LeftParenthesisKeyword_0_0_RightParenthesisKeyword_0_2__q;
	protected AbstractElementAlias match_XImportDeclaration_SemicolonKeyword_2_q;
	protected AbstractElementAlias match_XParenthesizedExpression_LeftParenthesisKeyword_0_a;
	protected AbstractElementAlias match_XParenthesizedExpression_LeftParenthesisKeyword_0_p;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (EmfParsleyDslGrammarAccess) access;
		match_FeaturesProvider___FeaturesKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getFeaturesProviderAccess().getFeaturesKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getFeaturesProviderAccess().getLeftCurlyBracketKeyword_3_1()), new TokenAlias(false, false, grammarAccess.getFeaturesProviderAccess().getRightCurlyBracketKeyword_3_3()));
		match_FormControlFactory___ControlKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getFormControlFactoryAccess().getControlKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getFormControlFactoryAccess().getLeftCurlyBracketKeyword_3_1()), new TokenAlias(false, false, grammarAccess.getFormControlFactoryAccess().getRightCurlyBracketKeyword_3_3()));
		match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3())));
		match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()));
		match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___or_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a_RightCurlyBracketKeyword_3_1_3__q___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a__a__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3())), new GroupAlias(true, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1())), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()), new GroupAlias(true, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1())))), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()))));
		match_LabelProvider___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()));
		match_LabelProvider___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()));
		match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3())));
		match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()));
		match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___or_____TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a_RightCurlyBracketKeyword_3_0_3__q___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a__a__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3())), new GroupAlias(true, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1())), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()), new GroupAlias(true, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3())), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1())))), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()))));
		match_LabelProvider_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___or___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3____a = new AlternativeAlias(true, true, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getImageKeyword_3_1_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_1_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_1_3())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getTextKeyword_3_0_0()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getLeftCurlyBracketKeyword_3_0_1()), new TokenAlias(false, false, grammarAccess.getLabelProviderAccess().getRightCurlyBracketKeyword_3_0_3())));
		match_Module___PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1_RightCurlyBracketKeyword_4_6_3__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getModuleAccess().getPartsKeyword_4_6_0()), new TokenAlias(false, false, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_4_6_1()), new TokenAlias(false, false, grammarAccess.getModuleAccess().getRightCurlyBracketKeyword_4_6_3()));
		match_Module___RightCurlyBracketKeyword_4_6_3_PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getModuleAccess().getRightCurlyBracketKeyword_4_6_3()), new TokenAlias(false, false, grammarAccess.getModuleAccess().getPartsKeyword_4_6_0()), new TokenAlias(false, false, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_4_6_1()));
		match_PropertyDescriptionProvider___TextKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getPropertyDescriptionProviderAccess().getTextKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getPropertyDescriptionProviderAccess().getLeftCurlyBracketKeyword_3_1()), new TokenAlias(false, false, grammarAccess.getPropertyDescriptionProviderAccess().getRightCurlyBracketKeyword_3_3()));
		match_ViewerContentProvider___ChildrenKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getViewerContentProviderAccess().getChildrenKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getViewerContentProviderAccess().getLeftCurlyBracketKeyword_3_1()), new TokenAlias(false, false, grammarAccess.getViewerContentProviderAccess().getRightCurlyBracketKeyword_3_3()));
		match_XBlockExpression_SemicolonKeyword_2_1_q = new TokenAlias(false, true, grammarAccess.getXBlockExpressionAccess().getSemicolonKeyword_2_1());
		match_XConstructorCall___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getXConstructorCallAccess().getLeftParenthesisKeyword_4_0()), new TokenAlias(false, false, grammarAccess.getXConstructorCallAccess().getRightParenthesisKeyword_4_2()));
		match_XExpressionInClosure_SemicolonKeyword_1_1_q = new TokenAlias(false, true, grammarAccess.getXExpressionInClosureAccess().getSemicolonKeyword_1_1());
		match_XFunctionTypeRef___LeftParenthesisKeyword_0_0_RightParenthesisKeyword_0_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getXFunctionTypeRefAccess().getLeftParenthesisKeyword_0_0()), new TokenAlias(false, false, grammarAccess.getXFunctionTypeRefAccess().getRightParenthesisKeyword_0_2()));
		match_XImportDeclaration_SemicolonKeyword_2_q = new TokenAlias(false, true, grammarAccess.getXImportDeclarationAccess().getSemicolonKeyword_2());
		match_XParenthesizedExpression_LeftParenthesisKeyword_0_a = new TokenAlias(true, true, grammarAccess.getXParenthesizedExpressionAccess().getLeftParenthesisKeyword_0());
		match_XParenthesizedExpression_LeftParenthesisKeyword_0_p = new TokenAlias(true, false, grammarAccess.getXParenthesizedExpressionAccess().getLeftParenthesisKeyword_0());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getArrayBracketsRule())
			return getArrayBracketsToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getOpSingleAssignRule())
			return getOpSingleAssignToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * ArrayBrackets :
	 * 	'[' ']'
	 * ;
	 */
	protected String getArrayBracketsToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "[]";
	}
	
	/**
	 * OpSingleAssign:
	 * 	'='
	 * ;
	 */
	protected String getOpSingleAssignToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "=";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_FeaturesProvider___FeaturesKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q.equals(syntax))
				emit_FeaturesProvider___FeaturesKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_FormControlFactory___ControlKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q.equals(syntax))
				emit_FormControlFactory___ControlKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a__a.equals(syntax))
				emit_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a.equals(syntax))
				emit_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___or_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a_RightCurlyBracketKeyword_3_1_3__q___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a__a__.equals(syntax))
				emit_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___or_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a_RightCurlyBracketKeyword_3_1_3__q___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a__a__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a.equals(syntax))
				emit_LabelProvider___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a.equals(syntax))
				emit_LabelProvider___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a__a.equals(syntax))
				emit_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a.equals(syntax))
				emit_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___or_____TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a_RightCurlyBracketKeyword_3_0_3__q___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a__a__.equals(syntax))
				emit_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___or_____TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a_RightCurlyBracketKeyword_3_0_3__q___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a__a__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_LabelProvider_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___or___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3____a.equals(syntax))
				emit_LabelProvider_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___or___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3____a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Module___PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1_RightCurlyBracketKeyword_4_6_3__a.equals(syntax))
				emit_Module___PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1_RightCurlyBracketKeyword_4_6_3__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Module___RightCurlyBracketKeyword_4_6_3_PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1__a.equals(syntax))
				emit_Module___RightCurlyBracketKeyword_4_6_3_PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_PropertyDescriptionProvider___TextKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q.equals(syntax))
				emit_PropertyDescriptionProvider___TextKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ViewerContentProvider___ChildrenKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q.equals(syntax))
				emit_ViewerContentProvider___ChildrenKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_XBlockExpression_SemicolonKeyword_2_1_q.equals(syntax))
				emit_XBlockExpression_SemicolonKeyword_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_XConstructorCall___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q.equals(syntax))
				emit_XConstructorCall___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_XExpressionInClosure_SemicolonKeyword_1_1_q.equals(syntax))
				emit_XExpressionInClosure_SemicolonKeyword_1_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_XFunctionTypeRef___LeftParenthesisKeyword_0_0_RightParenthesisKeyword_0_2__q.equals(syntax))
				emit_XFunctionTypeRef___LeftParenthesisKeyword_0_0_RightParenthesisKeyword_0_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_XImportDeclaration_SemicolonKeyword_2_q.equals(syntax))
				emit_XImportDeclaration_SemicolonKeyword_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_XParenthesizedExpression_LeftParenthesisKeyword_0_a.equals(syntax))
				emit_XParenthesizedExpression_LeftParenthesisKeyword_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_XParenthesizedExpression_LeftParenthesisKeyword_0_p.equals(syntax))
				emit_XParenthesizedExpression_LeftParenthesisKeyword_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     ('features' '{' '}')?
	 */
	protected void emit_FeaturesProvider___FeaturesKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('control' '{' '}')?
	 */
	protected void emit_FormControlFactory___ControlKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('image' '{' '}' ('text' '{' '}')*)*
	 */
	protected void emit_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('image' '{' '}')*
	 */
	protected void emit_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (
	     ('image' '{' ('}' 'image' '{')*) | 
	     (
	         ('image' '{' ('}' 'image' '{')* '}')? 
	         ('text' '{' '}')+ 
	         'image' 
	         '{' 
	         ('}' 'image' '{')* 
	         ('}' ('text' '{' '}')+ 'image' '{' ('}' 'image' '{')*)*
	     )
	 )
	 */
	protected void emit_LabelProvider___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___or_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a_RightCurlyBracketKeyword_3_1_3__q___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__p_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1___RightCurlyBracketKeyword_3_1_3_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a__a__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('}' ('image' '{' '}')* 'text' '{')*
	 */
	protected void emit_LabelProvider___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('}' ('text' '{' '}')* 'image' '{')*
	 */
	protected void emit_LabelProvider___RightCurlyBracketKeyword_3_1_3___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a_ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('text' '{' '}' ('image' '{' '}')*)*
	 */
	protected void emit_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__a__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('text' '{' '}')*
	 */
	protected void emit_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (
	     ('text' '{' ('}' 'text' '{')*) | 
	     (
	         ('text' '{' ('}' 'text' '{')* '}')? 
	         ('image' '{' '}')+ 
	         'text' 
	         '{' 
	         ('}' 'text' '{')* 
	         ('}' ('image' '{' '}')+ 'text' '{' ('}' 'text' '{')*)*
	     )
	 )
	 */
	protected void emit_LabelProvider___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___or_____TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a_RightCurlyBracketKeyword_3_0_3__q___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a___RightCurlyBracketKeyword_3_0_3___ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3__p_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1___RightCurlyBracketKeyword_3_0_3_TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1__a__a__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (('text' '{' '}') | ('image' '{' '}'))*
	 */
	protected void emit_LabelProvider_____ImageKeyword_3_1_0_LeftCurlyBracketKeyword_3_1_1_RightCurlyBracketKeyword_3_1_3___or___TextKeyword_3_0_0_LeftCurlyBracketKeyword_3_0_1_RightCurlyBracketKeyword_3_0_3____a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('parts' '{' '}')*
	 */
	protected void emit_Module___PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1_RightCurlyBracketKeyword_4_6_3__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('}' 'parts' '{')*
	 */
	protected void emit_Module___RightCurlyBracketKeyword_4_6_3_PartsKeyword_4_6_0_LeftCurlyBracketKeyword_4_6_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('text' '{' '}')?
	 */
	protected void emit_PropertyDescriptionProvider___TextKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('children' '{' '}')?
	 */
	protected void emit_ViewerContentProvider___ChildrenKeyword_3_0_LeftCurlyBracketKeyword_3_1_RightCurlyBracketKeyword_3_3__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ';'?
	 */
	protected void emit_XBlockExpression_SemicolonKeyword_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('(' ')')?
	 */
	protected void emit_XConstructorCall___LeftParenthesisKeyword_4_0_RightParenthesisKeyword_4_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ';'?
	 */
	protected void emit_XExpressionInClosure_SemicolonKeyword_1_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('(' ')')?
	 */
	protected void emit_XFunctionTypeRef___LeftParenthesisKeyword_0_0_RightParenthesisKeyword_0_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ';'?
	 */
	protected void emit_XImportDeclaration_SemicolonKeyword_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '('*
	 */
	protected void emit_XParenthesizedExpression_LeftParenthesisKeyword_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '('+
	 */
	protected void emit_XParenthesizedExpression_LeftParenthesisKeyword_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
