package com.structis.vip.client.message;

/**
 * Interface to represent the messages contained in resource bundle: D:/JAVAROOT/vip/src/main/resources/com/structis/client/Messages.properties'.
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {

    /**
     * Translated "Enter your name".
     * 
     * @return translated "Enter your name"
     */
    @Key("nameField")
    String nameField();

    /**
     * Translated "Send".
     * 
     * @return translated "Send"
     */
    @Key("sendButton")
    String sendButton();

    /* ### Ecran Administration */
    @Key("main.tab.delegation")
    String delegationTab();

    /* ### Ecran principale */
    @Key("main.title")
    String mainTitle();

    @Key("main.slogan")
    String mainSlogan();

    @Key("main.acceuil")
    String mainMenuAcceuil();

    @Key("main.listefichecoutglobal")
    String mainMenuListeFicheCoutGlobal();

    @Key("main.listeproduit")
    String mainMenuListeProduit();

    @Key("main.administration")
    String mainAdministration();

    @Key("main.startariance")
    String mainAriane();

    @Key("main.version")
    String mainVersion();

    /* #### COMMON ECRAN #### */
    @Key("common.choosetosyn")
    String commonchoosetosyn();

    @Key("common.code")
    String commoncode();

    @Key("common.nom")
    String commonnom();

    @Key("common.nomme")
    String commonnomme();

    @Key("common.confirme")
    String commonconfirme();

    @Key("common.languageFrench")
    String commonlanguageFrench();

    @Key("common.languageEnglish")
    String commonlanguageEnglish();

    @Key("common.loadingdata")
    String commonloadingdata();

    @Key("common.alert")
    String commonalert();

    @Key("common.syncdata")
    String commonsyncdata();

    @Key("common.creerbutton")
    String commonCreerbutton();

    @Key("common.addbutton")
    String commonAddButton();

    @Key("common.closebutton")
    String commonClosebutton();

    @Key("common.applybutton")
    String commonApplybutton();

    @Key("common.servererror")
    String commonServererror();

    @Key("common.annulerbutton")
    String commonAnnulerButton();

    @Key("common.consulterbutton")
    String commonConsulterbutton();

    @Key("common.validerbutton")
    String commonValiderButton();

    @Key("common.dialogouibutton")
    String commonDialogOuiButton();

    @Key("common.dialognonbutton")
    String commonDialogNonButton();

    @Key("common.erreurheader")
    String commonErreurHeader();

    @Key("common.infoheader")
    String commonInfoHeader();

    @Key("common.dialogheader")
    String commonDialogHeader();

    @Key("common.dialogfooter")
    String commonDialogFooter();

    @Key("common.majsucces")
    String commonMajSucces();

    @Key("common.uploadbutton")
    String commonUploadButton();

    @Key("common.fermerbutton")
    String commonFermerButton();

    @Key("common.modifierbutton")
    String commonModifierButton();

    @Key("common.validationuploaderrormessage")
    String commonValidationUploadErrorMessage();

    @Key("common.modelinvalide")
    String commonModelInvalide();

    @Key("common.modelincomplet")
    String commonModelIncomplet();

    @Key("common.imageautoriser")
    String commonImageAutoriser();

    @Key("common.upbutton")
    String commUpButton();

    @Key("common.downbutton")
    String commDownButton();

    @Key("common.numerique")
    String commonNumerique();

    @Key("common.supprimer")
    String commonSupprimer();

    @Key("common.enregistrer")
    String commonEnregistrer();

    @Key("common.creationbutton")
    String commonCreationButton();

    @Key("common.techerreurheader")
    String commonTechErreurHeader();

    @Key("common.foncterreurheader")
    String commonFonctErreurHeader();

    @Key("common.erreurinconnu")
    String commonErreurInconnu();

    @Key("common.delete.message")
    String commonDeleteMessage(String name);

    @Key("common.delete.collaborateur.message")
    String commonDeleteCollaborateurMessage(String name);

    @Key("common.recherchebutton")
    String commonRechercheButton();

    @Key("common.confirmation")
    String commonConfirmation();

    @Key("common.oui")
    String commonOui();

    @Key("common.non")
    String commonNon();

    @Key("common.toutes")
    String commonToutes();

    @Key("common.encours")
    String commonEncours();

    @Key("common.perimetre")
    String commonPerimetre();

    @Key("common.download")
    String commonDownload();

    @Key("common.tous")
    String commonTous();

    @Key("common.delegations")
    String commonDelegations();

    @Key("common.ajouterdelegationprincipale")
    String commonAjouterDelegationPrincipale();

    @Key("common.retoursalalistedesdelegations")
    String commonRetoursalaLstedesdelegations();

    @Key("common.modifierbutton")
    String commonmodifierbutton();

    @Key("common.rulebutton")
    String commonrulebutton();

    @Key("common.info")
    String commoninfo();

    @Key("common.error")
    String commonerror();

    @Key("common.toutcocher")
    String commontoutcocher();

    @Key("common.toutdecocher")
    String commontoutdecocher();

    @Key("common.validerlaselection")
    String commonvaliderlaselection();

    @Key("common.language")
    String commonlanguage();

    @Key("common.no.permission.perimetre")
    String commonnopermissionperimetre();

    @Key("common.no.permission.entite")
    String commonnopermissionentite(String entite);

    @Key("common.temporary")
    String commontemporary();

    @Key("common.no.permission")
    String commonnopermission();

    @Key("accueil.contentheader")
    String accueilContentHeader();

    @Key("delegation.grid.action")
    String action();

    @Key("delegation.grid.type")
    String type();

    @Key("delegation.grid.delegataire")
    String delegataire();

    @Key("delegation.grid.perimetre")
    String perimetre();

    @Key("delegation.grid.nature")
    String nature();

    @Key("delegation.grid.delegant")
    String delegant();

    @Key("delegation.grid.debutdevalidite")
    String debutdevalidite();

    @Key("delegation.grid.findevalidite")
    String findevalidite();

    @Key("delegation.grid.delegationsignee")
    String delegationsignee();

    @Key("delegation.entite")
    String delegationentite();

    @Key("delegation.uo")
    String delegationuo();

    @Key("delegation.nature")
    String delegationnature();

    @Key("delegation.status")
    String delegationstatus();

    @Key("delegation.type")
    String delegationtype();

    @Key("delegation.afficher")
    String delegationafficher();

    @Key("delegation.delegant")
    String delegationdelegant();

    @Key("delegation.delegataire")
    String delegationdelegataire();

    @Key("delegation.periode")
    String delegationperiode();

    @Key("delegation.au")
    String delegationau();

    @Key("delegation.sep")
    String delegationsep();

    @Key("delegation.delegationconjointe")
    String delegationdelegationconjointe();

    @Key("delegation.filtrer")
    String delegationfiltrer();

    @Key("delegation.header.suivants")
    String delegationheadersuivants();

    @Key("delegation.header.perimetre")
    String delegationheaderperimetre();

    @Key("delegation.label.path")
    String delegationlabelpath();

    @Key("delegation.combobox.action1")
    String delegationcomboboxaction1();

    @Key("delegation.combobox.action2")
    String delegationcomboboxaction2();

    @Key("delegation.combobox.action3")
    String delegationcomboboxaction3();

    @Key("delegation.combobox.action4")
    String delegationcomboboxaction4();

    @Key("delegation.combobox.action5")
    String delegationcomboboxaction5();

    @Key("delegation.combobox.action6")
    String delegationcomboboxaction6();

    @Key("delegation.combobox.action7")
    String delegationcomboboxaction7();

    @Key("delegation.combobox.action8")
    String delegationcomboboxaction8();

    @Key("delegation.combobox.action9")
    String delegationcomboboxaction9();

    @Key("delegation.combobox.action10")
    String delegationcomboboxaction10();

    @Key("delegationdetail.caption")
    String delegationdetailcaption();

    @Key("delegationdetail.button.retoursaux")
    String delegationdetailbuttonretoursaux();

    @Key("delegationdetail.button.printer")
    String delegationdetailbuttonprinter();

    @Key("delegationdetail.button.Modifier")
    String delegationdetailbuttonModifier();

    @Key("delegationdetail.label.typededelegation")
    String delegationdetaillabeltypededelegation();

    @Key("delegationdetail.label.demandeur")
    String delegationdetaillabeldemandeur();

    @Key("delegationdetail.label.status")
    String delegationdetaillabelstatus();

    @Key("delegationdetail.label.delegationsignee")
    String delegationdetaillabeldelegationsignee();

    @Key("delegationdetail.label.datedesignature")
    String delegationdetaillabeldatedesignature();

    @Key("delegationdetail.label.lieudesignature")
    String delegationdetaillabellieudesignature();

    @Key("delegationdetail.label.datededemande")
    String delegationdetaillabeldatededemande();

    @Key("delegationdetail.label.lieudedemande")
    String delegationdetaillabellieudedemande();

    @Key("delegationdetail.label.datedacceptation")
    String delegationdetaillabeldatedacceptation();

    @Key("delegationdetail.label.lieudedacceptation")
    String delegationdetaillabellieudedacceptation();

    @Key("delegationdetail.label.datederecommandation")
    String delegationdetaillabeldatederecommandation();

    @Key("delegationdetail.label.lieuderecommandation")
    String delegationdetaillabellieuderecommandation();

    @Key("delegationdetail.caption.partie")
    String delegationdetailcaptionpartie();

    @Key("delegationdetail.label.delegationprincipale")
    String delegationdetaillabeldelegationprincipale();

    @Key("delegationdetail.label.delagant")
    String delegationdetaillabeldelagant();

    @Key("delegationdetail.label.delegataire")
    String delegationdetaillabeldelegataire();

    @Key("delegationdetail.label.datededebut")
    String delegationdetaillabeldatededebut();

    @Key("delegationdetail.label.datedefin")
    String delegationdetaillabeldatedefin();

    @Key("delegationdetail.label.sep")
    String delegationdetaillabelsep();

    @Key("delegationdetail.radio.oui")
    String delegationdetailradiooui();

    @Key("delegationdetail.radio.non")
    String delegationdetailradionon();

    @Key("delegationdetail.label.conjounte")
    String delegationdetaillabelconjounte();

    @Key("delegationdetail.label.documents")
    String delegationdetaillabeldocuments();

    // Choose entity ecran
    @Key("chooseentity.header")
    String chooseentityheader();

    @Key("chooseentity.super.header")
    String chooseentitysuperheader();

    @Key("chooseentity.perimetre")
    String chooseentityperimetre();

    // New delegatation form
    @Key("delegationform.heading")
    String delegationformheading();

    @Key("delegationform.msg.nature.not.available")
    String delegationformmsgnaturenotavailable();

    @Key("delegationform.typedelegation")
    String delegationformtypedelegation();

    @Key("delegationform.demandeur")
    String delegationformdemandeur();

    @Key("delegationform.status")
    String delegationformstatus();

    @Key("delegationform.description")
    String delegationformdescription();

    @Key("delegationform.principale")
    String delegationformprincipale();

    @Key("delegationform.delegant")
    String delegationformdelegant();

    @Key("delegationform.delegataire")
    String delegationformdelegataire();

    @Key("delegationform.conjoin")
    String delegationformconjoin();

    @Key("delegationform.ajouter.signee")
    String delegationformajoutersignee();

    @Key("delegationform.les.documents")
    String delegationformlesdocuments();

    @Key("delegationform.document.signed")
    String delegationformdocumentsigned();

    @Key("delegationform.action")
    String delegationformaction();

    @Key("delegationform.documents")
    String delegationformdocuments();

    @Key("delegationform.annuler")
    String delegationformannuler();

    @Key("delegationform.modifier")
    String delegationformmodifier();

    @Key("delegationform.oui")
    String delegationformoui();

    @Key("delegationform.non")
    String delegationformnon();

    @Key("delegationform.date.debut")
    String delegationformdatedebut();

    @Key("delegationform.date.fin")
    String delegationformdatefin();

    @Key("delegationform.date.signature.proposition")
    String delegationformdatesignatureproposition();

    @Key("delegationform.etde.lieu.signature.proposition")
    String delegationformetdelieusignatureproposition();

    @Key("delegationform.byefe.lieu.signature.proposition")
    String delegationformbyefelieusignatureproposition();

    @Key("delegationform.date.signature")
    String delegationformdatesignature();

    @Key("delegationform.lieu.signature")
    String delegationformlieusignature();

    @Key("delegationform.date.signature.recommandation")
    String delegationformdatesignaturerecommandation();

    @Key("delegationform.lieu.signature.recommandation")
    String delegationformlieusignaturerecommandation();

    @Key("delegationform.etde.limite.commercial")
    String delegationformetdelimitecommercial();

    @Key("delegationform.byefe.limite.commercial")
    String delegationformbyefelimitecommercial();

    @Key("delegationform.etde.limite.avenants")
    String delegationformetdelimiteavenants();

    @Key("delegationform.byefe.limite.avenants")
    String delegationformbyefelimiteavenants();

    @Key("delegationform.etde.limite.devis")
    String delegationformetdelimitedevis();

    @Key("delegationform.byefe.limite.devis")
    String delegationformbyefelimitedevis();

    @Key("delegationform.etde.limite.entreprises")
    String delegationformetdelimiteentreprises();

    @Key("delegationform.byefe.limite.entreprises")
    String delegationformbyefelimiteentreprises();

    @Key("delegationform.limite.assurance")
    String delegationformlimiteassurance();

    @Key("delegationform.champs")
    String delegationformchamps();

    @Key("chooseentiteform.entite.error")
    String choosentiteformentiteerror();

    @Key("chooseentiteform.perimetre.error")
    String chooseentiteformperimetreerror();

    @Key("delegant.heading")
    String delegantheading();

    @Key("delegant.nom")
    String delegantnom();

    @Key("delegant.prenom")
    String delegantprenom();

    @Key("delegant.titre")
    String deleganttitre();

    @Key("delegant.statut")
    String delegantstatut();

    @Key("delegant.qualite")
    String delegantqualite();

    @Key("delegataire.heading")
    String delegataireheading();

    @Key("delegataire.nom")
    String delegatairenom();

    @Key("delegataire.prenom")
    String delegataireprenom();

    @Key("delegataire.titre")
    String delegatairetitre();

    @Key("delegataire.statut")
    String delegatairestatut();

    @Key("delegataire.qualite")
    String delegatairequalite();

    @Key("delegataire.date.naissance")
    String delegatairedatenaissance();

    @Key("delegataire.lieu.naissance")
    String delegatairelieunaissance();

    @Key("delegataire.nationalite")
    String delegatairenationalite();

    @Key("delegataire.adresse")
    String delegataireadresse();

    @Key("delegataire.date.formation")
    String delegatairedateformation();

    @Key("delegataire.intitule.formation")
    String delegataireintituleformation();

    @Key("societe.heading")
    String societeheading();

    @Key("societe.etde.nom")
    String societeetdenom();

    @Key("societe.byefe.nom")
    String societebyefenom();

    @Key("societe.etde.status.juridique")
    String societeetdestatusjuridique();

    @Key("societe.byefe.status.juridique")
    String societebyefestatusjuridique();

    @Key("societe.capital")
    String societecapital();

    @Key("societe.adresse")
    String societeadresse();

    @Key("societe.etde.siret")
    String societeetdesiret();

    @Key("societe.byefe.siret")
    String societebyefesiret();

    @Key("societe.etde.ville")
    String societeetdeville();

    @Key("societe.byefe.ville")
    String societebyefeville();

    @Key("chantier.nom")
    String chantiernom();

    @Key("chantier.ville")
    String chantierville();

    @Key("chantier.numero.projet")
    String chantiernumeroprojet();

    @Key("chantier.date.travaux")
    String chantierdatetravaux();

    @Key("chantier.date.previsionnelle")
    String chantierdateprevisionnelle();

    @Key("chantier.date.definitive")
    String chantierdatedefinitive();

    @Key("chantier.heading")
    String chantierheading();

    @Key("delegation.document.add.button")
    String delegationdocumentaddbutton();

    @Key("delegation.document.description")
    String delegationdocumentdescription();

    @Key("delegation.document.file")
    String delegationdocumentfile();

    @Key("delegation.document.action")
    String delegationdocumentaction();

    @Key("delegation.document.delete.message")
    String delegationdocumentdeletemessage();

    @Key("delegation.document.delete.failed")
    String delegationdocumentdeletefailed();

    @Key("delegation.document.dialog.heading")
    String delegationdocumentdialogheading();

    @Key("delegation.document.dialog.description")
    String delegationdocumentdialogdescription();

    @Key("delegation.document.dialog.file")
    String delegationdocumentdialogfile();

    @Key("delegation.document.dialog.file.exist")
    String delegationdocumentdialogfileexist();

    @Key("delegation.document.dialog.create.failed")
    String delegationdocumentdialogcreatefailed();

    @Key("delegation.document.dialog.create.success")
    String delegationdocumentdialogcreatesuccess();

    @Key("delegation.document.dialog.msg.accept.pdf")
    String delegationdocumentdialogmsgacceptpdf();

    @Key("delegation.renew.heading")
    String delegationrenewheading();

    @Key("delegation.duplicate.error")
    String delegationduplicateerror();

    @Key("delegation.same.delegant.delegantaire.error")
    String delegationsamedelegantdelegantaireerror();

    @Key("delegation.start.after.end.date.error")
    String delegationstartafterenddateerror();

    @Key("delegation.temp.of.temp.error")
    String delegationtemporaryoftemporaryerror();

    @Key("delegation.unique.delegant.error")
    String delegationuniquedeleganterror();

    @Key("delegation.model.rule.exist.error")
    String delegationmodelruleexisterror();

    @Key("delegation.model.exist.error")
    String delegationmodelexisterror();

    @Key("delegation.model.delete.error")
    String delegationmodeldeleteerror();

    @Key("delegation.model.delete.successfully")
    String delegationmodeldeletesuccessfully();

    @Key("delegation.model.add.button")
    String delegationmodeladdbutton();

    @Key("delegation.model.rule.add.button")
    String delegationmodelruleaddbutton();

    @Key("delegation.model.rule.add.delegant.button")
    String delegationmodelruleadddelegantbutton();

    @Key("delegation.model.add.document.button")
    String delegationmodeladddocumentbutton();

    @Key("delegation.model.document.header")
    String delegationmodeldocumentheader();

    @Key("delegation.model.perimetre.type")
    String delegationmodelperimetretype();

    @Key("delegation.model.delegant.type")
    String delegationmodeldeleganttype();

    @Key("delegation.model.document")
    String delegationmodeldocument();

    @Key("delegation.model.action")
    String delegationmodelaction();

    @Key("delegation.model.heading")
    String delegationmodelheading();

    @Key("delegation.model.rule.heading")
    String delegationmodelruleheading();

    @Key("delegation.model.update.success")
    String delegationmodelupdatesuccess();

    @Key("delegation.model.update.failed")
    String delegationmodelupdatefailed();

    @Key("delegation.model.save.success")
    String delegationmodelsavesuccess();

    @Key("delegation.model.save.failed")
    String delegationmodelsavefailed();

    @Key("delegation.model.not.found")
    String delegationmodelnotfound();

    @Key("delegation.model.deleted")
    String delegationmodeldeleted();

    @Key("delegation.model.delete.confirm")
    String delegationmodeldeleteconfirm();

    @Key("delegation.model.document.required")
    String delegationmodeldocumentrequired();

    @Key("delegation.rule.back")
    String delegationruleback();

    @Key("delegation.document.heading")
    String delegationdocumentheading();

    @Key("field.rules.header")
    String fieldrulesheader();

    @Key("field.rules.save")
    String fieldrulessave();

    @Key("field.rules.displayed")
    String fieldrulesdisplayed();

    @Key("field.rules.required")
    String fieldrulesrequired();

    @Key("field.rules.label")
    String fieldruleslabel();

    @Key("field.rules.dbfield")
    String fieldrulesdbfield();

    @Key("document.name")
    String documentname();

    @Key("document.file")
    String documentfile();

    @Key("document.type")
    String documenttype();

    @Key("document.file.temp")
    String documentfiletemp();
    
    @Key("document.file.subDel")
    String documentFileSubDelegation();

    @Key("document.language")
    String documentlanguage();

    @Key("document.version")
    String documentversion();

    @Key("document.documentform")
    String documentform();

    @Key("document.back")
    String documentback();

    @Key("docuemnt.fileexist")
    String documentfileexist();

    @Key("document.filename")
    String documentfilename();

    @Key("document.listdocuments")
    String documentlistdocuments();

    @Key("document.create")
    String documentcreate();

    @Key("document.delete.is.used.delegation.model")
    String documentdeleteisuseddelegationmodel();

    @Key("document.delete.is.used.delegation")
    String documentdeleteisuseddelegation();

    @Key("document.insert.duplicate.name")
    String documentinsertduplicatename();

    // COLLABORATURE LIST - FORM
    @Key("collaborature.nom")
    String collaboraturenom();

    @Key("collaborature.nometprenom")
    String collaboraturenometprenom();

    @Key("collaborature.back")
    String collaboratureback();

    @Key("collaborature.listedescollaboratures")
    String collaboraturelistedescollaboratures();

    @Key("collaborature.rechercher")
    String collaboraturerechercher();

    @Key("collaborature.button.importdepuisrubis")
    String collaboraturebuttonimportdepuisrubis();

    @Key("collaborature.importersucces")
    String collaboratureimportersucces();

    @Key("collaborature.message.delete")
    String collaboraturemessagedelete();

    @Key("collaborature.message.delete.successfully")
    String collaboraturemessagedeletesuccessfully();

    @Key("collaborature.civilite")
    String collaboraturecivilite();

    @Key("collaborature.prenom")
    String collaboratureprenom();

    @Key("collaborature.datedenaissance")
    String collaboraturedatedenaissance();

    @Key("collaborature.lieudenaissance")
    String collaboraturelieudenaissance();

    @Key("collaborature.Nationalite")
    String collaboratureNationalite();

    @Key("collaborature.adressepersonnelle")
    String collaboratureadressepersonnelle();

    @Key("collaborature.datemiseajourrubis")
    String collaboraturedatemiseajourrubis();

    @Key("collaborature.societerubis")
    String collaboraturesocieterubis();

    @Key("collaborature.matriculerubis")
    String collaboraturematriculerubis();

    @Key("collaborature.dateentregroup")
    String collaboraturedateentregroup();

    @Key("collaborature.datedesortiesociete")
    String collaboraturedatedesortiesociete();

    @Key("collaborature.societesiexterne")
    String collaboraturesocietesiexterne();

    @Key("collaborature.niveauhierarchique")
    String collaboratureniveauhierarchique();

    @Key("collaborature.sorti")
    String collaboraturesorti();

    @Key("collaborature.collaborateurdelegant")
    String collaboraturecollaborateurdelegant();

    @Key("collaborature.collaborateurdelegataire")
    String collaboraturecollaborateurdelegataire();

    @Key("collaborature.formationaladelegation")
    String collaboratureformationaladelegation();

    @Key("collaborature.datedeformatonaladelegation")
    String collaboraturedatedeformatonaladelegation();

    @Key("collaborature.qualitedudelegant")
    String collaboraturequalitedudelegant();

    @Key("collaborature.dateduconseiladministration")
    String collaboraturedateduconseiladministration();

    @Key("collaborature.statutduconseiladministration")
    String collaboraturestatutduconseiladministration();

    @Key("collaborature.aeffetdu")
    String collaboratureaeffetdu();

    @Key("collaborature.datedelegation")
    String collaboraturedatedelegation();

    @Key("collaborature.delegant")
    String collaboraturedelegant();

    @Key("collaborature.typedelegant")
    String collaboraturetypedelegant();

    @Key("collaborature.qualitecollaborateurdelegant")
    String collaboraturequalitecollaborateurdelegant();

    // NATURE LIST - FORM
    @Key("nature.back")
    String natureback();

    @Key("nature.nom")
    String naturenom();

    @Key("nature.listedesnatures")
    String naturelistedesnatures();

    @Key("nature.form.description")
    String natureformdescription();

    @Key("nature.form.header")
    String natureformheader();

    @Key("nature.message.delete")
    String naturemessagedelete();

    @Key("nature.message.delete.successfully")
    String naturemessagedeletesuccessfully();

    @Key("nature.msg.delete.exist.delegation")
    String naturemsgdeleteexistdelegation();

    @Key("nature.msg.delete.exist.delegation.model")
    String naturemsgdeleteexistdelegationmodel();

    // LANGUE LIST - FORM
    @Key("language.nom")
    String languagenom();

    @Key("language.code")
    String languagecode();

    @Key("language.default")
    String languagedefault();

    @Key("language.listedeslangues")
    String languagelistedeslangues();

    @Key("language.form.header")
    String languageformheader();

    @Key("language.back")
    String languageback();

    @Key("language.message.delete")
    String languagemessagedelete();

    @Key("language.message.delete.successfully")
    String languagemessagedeletesuccessfully();

    @Key("language.msg.delete.exist.entite")
    String languagemsgdeleteexistentite();

    @Key("language.msg.delete.exist.perimetre")
    String languagemsgdeleteexistperimetre();

    @Key("language.msg.delete.exist.delegationmodel")
    String languagemsgdeleteexistdelegationmodel();

    @Key("language.msg.delete.exist.documentmodel")
    String languagemsgdeleteexistdocumentmodel();

    @Key("language.msg.delete.exist.field")
    String languagemsgdeleteexistfield();

    @Key("language.msg.delete.default")
    String languagemsgdeletedefault();

    // STATUS LIST - FORM
    @Key("status.nom")
    String statusnom();

    @Key("status.description")
    String statusdescription();

    @Key("status.listedesstatuts")
    String statuslistedesstatuts();

    @Key("status.form.header")
    String statusformheader();

    @Key("status.back")
    String statusback();

    @Key("status.message.delete")
    String statusmessagedelete();

    @Key("status.message.delete.successfully")
    String statusmessagedeletesuccessfully();

    // CHANTIER TYPE LIST - FORM
    @Key("chantier.type.label")
    String chantiertypelabel();

    @Key("chantier.type.endDate")
    String chantiertypeendDate();

    @Key("chantier.type.chantierSubdelegable")
    String chantiertypechantierSubdelegable();

    @Key("chantier.type.listedeschantiers")
    String chantiertypelistedeschantiers();

    @Key("chantier.type.form.header")
    String chantiertypeformheader();

    @Key("chantier.type.back")
    String chantiertypeback();

    @Key("chantier.type.message.delete")
    String chantiertypemessagedelete();

    @Key("chantier.type.message.delete.successfully")
    String chantiertypemessagedeletesuccessfully();

    @Key("chantier.type.msg.delete.exist")
    String chantiertypemsgdeleteexist();

    // FORMATION LIST - FORM
    @Key("formation.nom")
    String formationnom();

    @Key("formation.date")
    String formationdate();

    @Key("formation.description")
    String formationdescription();

    @Key("formation.form.header")
    String formationformheader();

    @Key("formation.back")
    String formationback();

    @Key("formation.message.delete")
    String formationmessagedelete();

    @Key("formation.listedesformations")
    String formationlistedesformations();

    @Key("formation.message.delete.successfully")
    String formationmessagedeletesuccessfully();

    @Key("formation.error")
    String formationerror();

    // CONTROL TYPE LIST - FORM
    @Key("controltype.nom")
    String controltypenom();

    @Key("controltype.description")
    String controltypedescription();

    @Key("controltype.listedestypedecontrol")
    String controltypelistedestypedecontrol();

    @Key("controltype.form.header")
    String controltypeformheader();

    @Key("controltype.back")
    String controltypeback();

    @Key("controltype.message.delete")
    String controltypemessagedelete();

    @Key("controltype.message.delete.successfully")
    String controltypemessagedeletesuccessfully();

    // DELEGATION TYPE LIST - FORM
    @Key("delegation.type.nom")
    String delegationtypenom();

    @Key("delegation.type.description")
    String delegationtypedescription();

    @Key("delegation.type.list")
    String delegationtypelist();

    @Key("delegation.type.form.header")
    String delegationtypeformheader();

    @Key("delegation.type.back")
    String delegationtypeback();

    @Key("delegation.type.insert.duplicated")
    String delegationtypeinsertduplicated();

    // CHOOSE PERIMETRE TYPE - DIALOG
    @Key("perimetre.type.choose.heading")
    String perimetretypechooseheading();

    @Key("perimetre.type.choose.select.all")
    String perimetretypechooseselectall();

    @Key("perimetre.type.choose.deselect.all")
    String perimetretypechoosedeselectall();

    @Key("perimetre.type.choose.sync")
    String perimetretypechoosesync();

    @Key("perimetre.type.choose.close")
    String perimetretypechooseclose();

    @Key("perimetre.type.choose.sync.start")
    String perimetretypechoosesyncstart();

    @Key("perimetre.type.choose.sync.progress")
    String perimetretypechoosesyncprogress();

    @Key("perimetre.type.choose.sync.failed")
    String perimetretypechoosesyncfailed();

    @Key("perimetre.type.choose.sync.success")
    String perimetretypechoosesyncsuccess();

    @Key("perimetre.type.choose.sync.info")
    String perimetretypechoosesyncinfo();

    @Key("perimetre.type.choose.perimetre")
    String perimetretypechooseperimetre();

    // PERIMETRE FORM
    @Key("perimetre.not.select")
    String perimetrenotselect();

    @Key("perimetre.title")
    String perimetretitle();

    @Key("perimetre.libelle")
    String perimetrelibelle();

    @Key("perimetre.argos.name")
    String perimetreargosname();

    @Key("perimetre.type")
    String perimetretype();

    @Key("perimetre.typeChantier")
    String perimetretypechantier();

    @Key("perimetre.debut")
    String perimetredebut();

    @Key("perimetre.previsionnelle")
    String perimetreprevisionnelle();

    @Key("perimetre.definitive")
    String perimetredefinitive();

    @Key("perimetre.code")
    String perimetrecode();

    @Key("perimetre.ville")
    String perimetreville();

    @Key("perimetre.adresse")
    String perimetreadresse();

    @Key("perimetre.entite.juridique")
    String perimetreentitejuridique();

    @Key("perimetre.langues")
    String perimetrelangues();

    @Key("perimetre.update.success")
    String perimetreupdatesuccess();

    @Key("perimetre.update.failed")
    String perimetreupdatefailed();

    @Key("perimetre.save.success")
    String perimetresavesuccess();

    @Key("perimetre.save.failed")
    String perimetresavefailed();

    @Key("perimetre.delete.confirm")
    String perimetredeleteconfirm();

    @Key("perimetre.delete.failed")
    String perimetredeletefailed();

    @Key("perimetre.delete.success")
    String perimetredeletesuccess();

    @Key("perimetre.delete.has.children")
    String perimetredeletehaschildren();

    @Key("perimetre.delete.is.used.delegation")
    String perimetredeleteisuseddelegation();

    @Key("perimetre.delete.is.used.collaborateur")
    String perimetredeleteisusedcollaborateur();

    @Key("perimetre.delete.is.used.role")
    String perimetredeleteisusedrole();

    @Key("perimetre.delete.is.used.user")
    String perimetredeleteisuseduser();

    @Key("perimetre.form")
    String perimetreform();

    // HEADER
    @Key("header.menu")
    String headermenu();

    @Key("header.menu.change.entite")
    String headermenuchangeentite();

    @Key("header.menu.switch.admin")
    String headermenuswitchadmin();

    @Key("header.menu.switch.delegation")
    String headermenuswitchdelegation();

    // USER LIST - FORM
    @Key("user.header")
    String userheader();

    @Key("user.username")
    String userusername();

    @Key("user.lastname")
    String userlastname();

    @Key("user.firstname")
    String userfirstname();

    @Key("user.update.success")
    String userupdatesuccess();

    @Key("user.update.failed")
    String userupdatefailed();

    @Key("user.save.success")
    String usersavesuccess();

    @Key("user.save.failed")
    String usersavefailed();

    @Key("user.delete.comfirm")
    String userdeletecomfirm();

    @Key("user.delete.success")
    String userdeletesuccess();

    @Key("user.delete.failed")
    String userdeletefailed();

    @Key("user.back")
    String userback();

    @Key("user.form")
    String userform();

    @Key("user.roles.header")
    String userrolesheader();

    @Key("user.roles.role")
    String userrolesrole();

    @Key("user.roles.perimetre")
    String userrolesperimetre();

    @Key("user.roles.apply")
    String userrolesapply();

    @Key("collaborature.button.apply.perietre.delegant")
    String collaboraturebuttonapplyperietredelegant();

    @Key("collaborature.button.apply.perietre.delegataire")
    String collaboraturebuttonapplyperietredelegataire();

    @Key("collaborature.perimetre.delegant")
    String perimetredelegant();

    @Key("collaborature.perimetre.delegataire")
    String perimetredelegataire();

    @Key("collaborature.delete.is.used.delegation")
    String collaboraturedeleteisuseddelegation();

    @Key("user.duplicated")
    String userduplicated();

    @Key("user.username.not.blank")
    String userusernamenotblank();

    @Key("user.roles.remove.confirm")
    String userrolesremoveconfirm();

    @Key("user.roles.apply.confirm")
    String userrolesapplyconfirm();

    @Key("user.roles.cannot.apply")
    String userrolescannotapply();

    @Key("user.roles.nonuo.cannot.apply")
    String userrolesnonuocannotapply();

    @Key("user.roles.add.message")
    String userrolesaddmessage();

    @Key("user.not.authorized")
    String usernotauthorized();

    @Key("user.login.header")
    String userloginheader();

    @Key("user.login.name")
    String userloginname();

    @Key("user.login.password")
    String userloginpasseword();

    @Key("user.login.domain")
    String userlogindomain();

    @Key("user.passeword.confirm")
    String userpassewordconfirm();

    @Key("user.password.invalid")
    String userpasswordinvalid();

    @Key("user.password.changed")
    String userpasswordchanged();

    // ADMIN TREE
    @Key("admin.tree.heading")
    String admintreeheading();

    @Key("admin.tree.button.ajouter")
    String admintreebuttonajouter();

    @Key("admin.tree.button.modifier")
    String admintreebuttonmodifier();

    @Key("admin.tree.button.supprimer")
    String admintreebuttonsupprimer();

    @Key("admin.tree.button.sync")
    String admintreebuttonsync();

    @Key("admin.tree.sync.etde")
    String admintreesyncetde();

    @Key("admin.tree.cree.no.etde")
    String admintreecreenoetde();

    @Key("admin.tree.supprimer.no.etde")
    String admintreesupprimernoetde();

    @Key("admin.tree.select.parent")
    String admintreeselectparent();

    @Key("admin.tree.select")
    String admintreeselect();

    @Key("change.password.popup.title")
    String changepasswordpopuptitle();

    @Key("password.and.confirm.different")
    String passwordandconfirmdifferent();

    // ENTITE JURIDIQUE
    @Key("entiteJuridique.list")
    String entiteJuridiqueList();

    @Key("entiteJuridique.back")
    String entiteJuridiqueBack();

    @Key("entiteJuridique.title")
    String entiteJuridiqueTitle();

    @Key("entiteJuridique.name")
    String entiteJuridiqueName();

    @Key("entiteJuridique.statut")
    String entiteJuridiqueStatut();

    @Key("entiteJuridique.capital")
    String entiteJuridiqueCapital();

    @Key("entiteJuridique.address")
    String entiteJuridiqueAddress();

    @Key("entiteJuridique.registrationId")
    String entiteJuridiqueRegistrationId();

    @Key("entiteJuridique.registrationAddress")
    String entiteJuridiqueRegistrationAddress();

    @Key("entiteJuridique.msg.delete.exist.delegation.error")
    String entiteJuridiquemsgdeleteexistdelegationerror();

    @Key("entiteJuridique.msg.delete.exist.error")
    String entiteJuridiquemsgdeleteexisterror();

    @Key("entiteJuridique.msg.delete.default.error")
    String entiteJuridiquemsgdeletedefaulterror();

    @Key("report.choose")
    String reportchoose();

    @Key("report.1.name")
    String report1name();

    @Key("report.2.name")
    String report2name();

    @Key("report.3.name")
    String report3name();

    @Key("report.4.name")
    String report4name();

    @Key("report.5.name")
    String report5name();

    @Key("report.6.name")
    String report6name();

    @Key("report.7.name")
    String report7name();

    @Key("report.8.name")
    String report8name();

    @Key("common.in.edit.mode")
    String commonineditmode();

    @Key("control.button.printer")
    String controldetailbuttonprinter();

    @Key("common.controls")
    String commonControls();

    @Key("common.ajouter.controle")
    String commonAjouterControle();

    @Key("control.action.consulter")
    String controlactionconsulter();

    @Key("control.action.modifier")
    String controlactionmodifier();

    @Key("control.action.exporter")
    String controlactionexporter();

    @Key("control.action.imprimer")
    String controlactionimprimer();

    @Key("control.action.supprimer")
    String controlactionsupprimer();

    @Key("control.form.heading")
    String controlformheading();

    @Key("control.organisation.nom")
    String controleorganisationnom();

    @Key("controle.perimetre.associe")
    String controleperimetreassocie();

    @Key("controle.code.projet")
    String controlecodeprojet();

    @Key("controle.type")
    String controletype();

    @Key("controle.internal")
    String controleinternal();

    @Key("controler.type")
    String controlertype();

    @Key("controler.internal")
    String controlerinternal();

    @Key("controler.external")
    String controlerexternal();

    @Key("controler.collaborateur")
    String controlercollaborateur();

    @Key("controler.external.fieldset")
    String controlerexternalfieldset();

    @Key("select.extern.controller")
    String selectexterncontroller();

    @Key("date.de.controle")
    String datedecontrole();

    @Key("controle.rapport.file")
    String controlerapportfile();

    @Key("retour.list.controle")
    String retourlistcontrole();

    @Key("control.grid.fullname")
    String controlgridfullname();

    @Key("controler.dialog.heading")
    String controlerdialogheading();

    @Key("admin.extcontroller.fname")
    String adminextcontrollerfname();

    @Key("admin.exccontroller.nationality")
    String adminexccontrollernationality();

    @Key("admin.exccontroller.address")
    String adminexccontrolleraddress();

    @Key("extcontroller.heading")
    String extcontrollerheading();

    @Key("admin.extcontroller.lname")
    String adminexccontrollerlname();

    @Key("extern.controller.message.delete.successfully")
    String externcontrollermessagedeletesuccessfully();

    @Key("extern.controller.back")
    String externcontrollerback();

    @Key("extern.controller.formheader")
    String externcontrollerformheader();

    @Key("control.grid.libelle.du.chantier")
    String controlgridlibelleduchantier();

    @Key("control.grid.ci.du.chantier")
    String controlgridciduchantier();

    @Key("control.grid.ci.code.projet")
    String controlgridcicodeprojet();

    @Key("control.grid.type.de.controle")
    String controlgridtypedecontrole();

    @Key("control.grid.date.du.controle")
    String controlgriddateducontrole();

    @Key("control.grid.nom.du.controleur.interne")
    String controlgridnomducontroleurinterne();

    @Key("control.grid.nom.du.controleur.externe")
    String controlgridnomducontroleurexterne();

    @Key("document.delete.document.message")
    String documentDeleteDocumentMessage(String name);

    @Key("document.message.delete.successfully")
    String documentmessagedeletesuccessfully();

    @Key("document.grid.nom")
    String documentgridnom();

    @Key("doc.back")
    String docback();

    @Key("doc.name")
    String docname();

    @Key("doc.comment")
    String doccomment();

    @Key("doc.name.not.blank")
    String docnamenotblank();

    @Key("doc.update.success")
    String docupdatesuccess();

    @Key("doc.update.failed")
    String docupdatefailed();

    @Key("doc.save.success")
    String docsavesuccess();

    @Key("doc.save.failed")
    String docsavefailed();

    @Key("document.nom")
    String documentnom();

    @Key("document.rechercher")
    String documentrechercher();

    @Key("document.liste.des.documents")
    String documentlistedesdocuments();

    @Key("document.header.form")
    String documentheaderform();

    @Key("perimetre.add.confirm")
    String perimetreaddconfirm(String name);

    @Key("common.Continue.Button")
    String commonContinueButton();

    @Key("perimetre.non.argos")
    String perimetrenonargos();

    @Key("perimetre.non.argos.value")
    String perimetrenonargosvalue();

    @Key("perimetre.groupment")
    String perimetregroupment();

    @Key("perimetre.partenaires")
    String perimetrepartenaires();

    @Key("perimetre.numero.projet")
    String perimetrenumeroprojet();

    @Key("perimetre.list.delegant.empty")
    String perimetrelistdelegantempty();

    @Key("perimetre.delegataire.delegant.notmap")
    String perimetredelegatairedelegantnotmap();

    @Key("category.form.header")
    String categoryformheader();

    @Key("category.nom")
    String categorynom();

    @Key("category.back")
    String categoryback();

    @Key("category.message.delete.successfully")
    String categorymessagedeletesuccessfully();

    @Key("category.liste.des.categories")
    String categorylistedescategories();

    @Key("doc.date")
    String docdate();

    @Key("doc.link")
    String doclink();

    @Key("user.role")
    String userrole();

    @Key("user.perimetre")
    String userperimetre();

    @Key("document.browse.text")
    String documentBrowseText();

    @Key("document.clear.upload.file")
    String documentclearuploadfile();

    @Key("controle.type.de.controle")
    String controletypedecontrole();

    @Key("controle.nom.du.controle")
    String controlenomducontrole();

    @Key("controle.date.de.fin")
    String controledatedefin();

    @Key("controle.date.de.debut")
    String controledatededebut();

    @Key("controle.controleur.intern.notexiste")
    String controleinternnotexiste();

    @Key("collaborature.zone")
    String collaboraturezone();

    @Key("collaborature.operations")
    String collaboratureoperations();

    @Key("admin.document.type.nom")
    String doctypenom();

    @Key("admin.document.type.delete.sucessfully")
    String doctypemessagedeletesuccessfully();

    @Key("admin.document.type.header")
    String doctypelistheader();

    @Key("admin.doc.type.form.header")
    String doctypeformheader();

    @Key("admin.doc.type.back")
    String doctypeback();

    @Key("admin.document.type.desc")
    String doctypedesc();

    @Key("collaborature.label.apply.perietre.delegant")
    String collaboraturelabelapplyperietredelegant();

    @Key("collaborature.label.apply.perietre.delegataire")
    String collaboraturelabelapplyperietredelegataire();

    @Key("delegation.combobox.action11")
    String delegationcomboboxaction11();

    @Key("collaborature.button.add.perietre.delegataire")
    String collaboraturebuttonaddperietredelegataire();

    @Key("delegation.sous.delegable")
    String delegationsousdelegable();

    @Key("perimetre.type.message.delete.successfully")
    String perimetretypemessagedeletesuccessfully();

    @Key("perimetre.type.msg.delete.exist")
    String perimetretypemsgdeleteexist();

    @Key("perimetre.type.Subdelegable")
    String perimetretypeSubdelegable();

    @Key("perimetre.type.name")
    String perimetretypename();

    @Key("perimetre.type.liste.des.types")
    String perimetretypelistedestypes();

    @Key("perimetre.type.form.header")
    String perimetretypeformheader();

    @Key("perimetre.type.back")
    String perimetretypeback();

    @Key("delegant.type.group.formheader")
    String deleganttypegroupformheader();

    @Key("delegant.type.group.nom")
    String deleganttypegroupnom();

    @Key("delegant.type.group.back")
    String deleganttypegroupback();

    @Key("delegant.type.group.liste.des.delegant.type.groups")
    String deleganttypegrouplistedesdeleganttypegroups();

    @Key("delegant.type.group.message.delete.successfully")
    String deleganttypegroupmessagedeletesuccessfully();

    @Key("delegant.type.group.group")
    String deleganttypegroupgroup();

    @Key("collaborateur.type.message.delete.successfully")
    String collaborateurtypemessagedeletesuccessfully();

    @Key("collaborateur.type.liste.des.collaborateur.types")
    String collaborateurtypelistedescollaborateurtypes();

    @Key("collaborateur.type.desc")
    String collaborateurtypedesc();

    @Key("collaborateur.type.group")
    String collaborateurtypegroup();

    @Key("collaborateur.type.nom")
    String collaborateurtypenom();

    @Key("delegant.type.formheader")
    String deleganttypeformheader();

    @Key("collaborateur.type.back")
    String collaborateurtypeback();

    @Key("perimetre.delete.manually.has.delegation")
    String perimetredeletemanuallyhasdelegation();

    @Key("perimetre.delete.manually.no.delegation")
    String perimetredeletemanuallynodelegation();

    @Key("perimetre.non.exist.in.argos")
    String perimetrenonexistinargos();

    @Key("perimetre.sync.error")
    String perimetresyncerror(String errorNames);

    // @Key("common.dialog.detail.button")
    // String commonDialogDetailButton();

}
