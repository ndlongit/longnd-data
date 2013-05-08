/****** Object:  Table [dbo].[Chantier]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Chantier](
	[id_chantier] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NULL,
	[b_archive] [bit] NULL,
	[date_modif] [datetime] NULL,
	[id_ref_si_travaux] [varchar](30) NULL,
	[prorata_theorique] [int] NULL,
 CONSTRAINT [PK_Chantier] PRIMARY KEY CLUSTERED 
(
	[id_chantier] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Lig_Transfert_PP]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Lig_Transfert_PP](
	[id_lig_transfert_pp] [int] IDENTITY(1,1) NOT NULL,
	[lot1] [varchar](100) NULL,
	[de_vers] [varchar](50) NULL,
	[lot2] [varchar](100) NULL,
	[quantite] [int] NULL,
	[pu] [float] NULL,
	[commentaires] [varchar](255) NULL,
	[id_transfert_pp] [int] NOT NULL,
	[id_chantier] [int] NOT NULL,
	[id_type_budg_conf] [int] NOT NULL,
 CONSTRAINT [PK_Lig_Transfert_PP] PRIMARY KEY CLUSTERED 
(
	[id_lig_transfert_pp] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_Type_Mch_AV]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_Type_Mch_AV](
	[id_type_mch_av] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_Type_Mch_AV] PRIMARY KEY CLUSTERED 
(
	[id_type_mch_av] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_Type_Lot]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_Type_Lot](
	[id_type_lot] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](40) NOT NULL,
	[ordre] [int] NULL,
	[inclus_dans_total] [bit] NULL,
 CONSTRAINT [PK_Ref_Type_Lot] PRIMARY KEY CLUSTERED 
(
	[id_type_lot] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_Type_Budj_Conf]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_Type_Budj_Conf](
	[id_type_budg_conf] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_Type_Budj_Conf] PRIMARY KEY CLUSTERED 
(
	[id_type_budg_conf] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_Transfert_PP]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_Transfert_PP](
	[id_transfert_pp] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_Transfert_PP] PRIMARY KEY CLUSTERED 
(
	[id_transfert_pp] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_Statut]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_Statut](
	[id_statut] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
	[info_libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_Statut] PRIMARY KEY CLUSTERED 
(
	[id_statut] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_Mode_Paiement]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_Mode_Paiement](
	[id_mode_paiement] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_Mode_Paiement] PRIMARY KEY CLUSTERED 
(
	[id_mode_paiement] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_DGD_Presente]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_DGD_Presente](
	[id_dgd_presente] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_DGD_Presente] PRIMARY KEY CLUSTERED 
(
	[id_dgd_presente] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_Decenale]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_Decenale](
	[id_decenale] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_Decenale] PRIMARY KEY CLUSTERED 
(
	[id_decenale] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ref_DDE_Agrement]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ref_DDE_Agrement](
	[id_dde_agrement] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](40) NULL,
 CONSTRAINT [PK_Ref_DDE_Agrement] PRIMARY KEY CLUSTERED 
(
	[id_dde_agrement] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UTILISATEUR_GRP]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UTILISATEUR_GRP](
	[id_utilisateur_grp] [int] IDENTITY(1,1) NOT NULL,
	[identifiant] [varchar](61) NULL,
	[b_admin] [bit] NULL,
 CONSTRAINT [PK_UTILISATEUR_GRP] PRIMARY KEY CLUSTERED 
(
	[id_utilisateur_grp] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Societe]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Societe](
	[id_societe] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](255) NOT NULL,
	[id_ref_si_travaux] [varchar](30) NOT NULL,
	[id_chantier] [int] NOT NULL,
 CONSTRAINT [PK_Societe] PRIMARY KEY CLUSTERED 
(
	[id_societe] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Lot]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Lot](
	[id_lot] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](100) NULL,
	[id_chantier] [int] NOT NULL,
	[id_ref_si_travaux] [varchar](30) NOT NULL,
 CONSTRAINT [PK_Lot_1] PRIMARY KEY CLUSTERED 
(
	[id_lot] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[b_contributeur] [bit] NULL,
	[b_lecteur] [bit] NULL,
	[id_utilisateur_grp] [int] NOT NULL,
	[id_chantier] [int] NOT NULL,
 CONSTRAINT [PK_Role_1] PRIMARY KEY CLUSTERED 
(
	[id_utilisateur_grp] ASC,
	[id_chantier] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Conducteur]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Conducteur](
	[id_conducteur] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](255) NOT NULL,
	[prenom] [varchar](255) NOT NULL,
	[id_ref_si_travaux] [varchar](30) NOT NULL,
	[id_chantier] [int] NOT NULL,
 CONSTRAINT [PK_Conducteur] PRIMARY KEY CLUSTERED 
(
	[id_conducteur] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fiche_Transfert_PP]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fiche_Transfert_PP](
	[date_modif] [datetime] NULL,
	[id_chantier] [int] NOT NULL,
	[id_transfert_pp] [int] NOT NULL,
	[objectif] [float] NULL,
 CONSTRAINT [PK_Fiche_Transfert_PP] PRIMARY KEY CLUSTERED 
(
	[id_chantier] ASC,
	[id_transfert_pp] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fiche_St]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fiche_St](
	[id_fiche_st] [int] IDENTITY(1,1) NOT NULL,
	[societe] [varchar](50) NULL,
	[conducteur] [varchar](50) NULL,
	[rg] [float] NULL,
	[objectif] [float] NULL,
	[date_dgd_presente] [datetime] NULL,
	[presta_pilotage] [float] NULL,
	[presta_assurances] [float] NULL,
	[presta_prorata] [float] NULL,
	[presta_canto] [float] NULL,
	[presta_badge] [float] NULL,
	[presta_grue] [float] NULL,
	[presta_lift] [float] NULL,
	[presta_benne] [float] NULL,
	[presta_nettoyage] [float] NULL,
	[date_marche_base] [datetime] NULL,
	[gest_budget_initial] [float] NULL,
	[gest_ecart_dernier_pt] [float] NULL,
	[gest_date_dernier_pt] [datetime] NULL,
	[avct_commentaires] [text] NULL,
	[acpt_commentaires] [text] NULL,
	[acpt_commentaires_internes] [varchar](50) NULL,
	[synth_ecart_m1] [float] NULL,
	[synth_ecart_der_pt] [float] NULL,
	[date_modif] [datetime] NULL,
	[id_chantier] [int] NULL,
	[id_lot] [int] NOT NULL,
	[id_type_lot] [int] NOT NULL,
	[id_mode_paiement] [int] NOT NULL,
	[id_decenale] [int] NOT NULL,
	[id_dde_agrement] [int] NOT NULL,
	[id_dgd_presente] [int] NOT NULL,
	[id_conducteur] [int] NULL,
	[id_societe] [int] NULL,
	[id_ref_si_travaux] [varchar](30) NOT NULL,
	[prorata_theorique] [int] NULL,
 CONSTRAINT [PK_Fiche_St] PRIMARY KEY CLUSTERED 
(
	[id_fiche_st] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Lig_Suiv_Gest]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Lig_Suiv_Gest](
	[id_lig_suiv_gest] [int] IDENTITY(1,1) NOT NULL,
	[devis] [varchar](60) NULL,
	[libelle] [varchar](50) NULL,
	[commentaires] [varchar](1000) NULL,
	[montant] [float] NULL,
	[montant_mch_av] [float] NULL,
	[montant_arrete] [float] NULL,
	[montant_non_arrete] [float] NULL,
	[provision] [float] NULL,
	[devis_refuse] [float] NULL,
	[activite_reel] [float] NULL,
	[libelle_budg_conf] [varchar](50) NULL,
	[montant_budg_conf] [float] NULL,
	[id_type_mch_av] [int] NULL,
	[id_statut] [int] NULL,
	[id_type_budg_conf] [int] NULL,
	[id_fiche_st] [int] NOT NULL,
	[lock] [tinyint] NULL,
 CONSTRAINT [PK_Lig_Suiv_Gest] PRIMARY KEY CLUSTERED 
(
	[id_lig_suiv_gest] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Lig_Retenue]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lig_Retenue](
	[id_lig_retenue] [int] IDENTITY(1,1) NOT NULL,
	[date] [datetime] NULL,
	[qte_canto] [int] NULL,
	[qte_badge] [int] NULL,
	[qte_grue] [int] NULL,
	[qte_lift] [int] NULL,
	[qte_benne] [int] NULL,
	[qte_nettoyage] [int] NULL,
	[autres] [float] NULL,
	[prorata] [float] NULL,
	[refacturation] [float] NULL,
	[id_fiche_st] [int] NOT NULL,
 CONSTRAINT [PK_Lig_Retenue] PRIMARY KEY CLUSTERED 
(
	[id_lig_retenue] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lig_Penalite]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Lig_Penalite](
	[id_lig_penalite] [int] IDENTITY(1,1) NOT NULL,
	[date] [datetime] NULL,
	[montant] [float] NULL,
	[commentaires] [varchar](1000) NULL,
	[id_fiche_st] [int] NOT NULL,
 CONSTRAINT [PK_Lig_Penalite] PRIMARY KEY CLUSTERED 
(
	[id_lig_penalite] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Lig_Avct]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Lig_Avct](
	[id_lig_avct] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](80) NULL,
	[date] [datetime] NULL,
	[cumule_avct] [FLOAT] NULL,
	[cumule_ret] [FLOAT] NULL,
	[id_fiche_st] [int] NOT NULL,
 CONSTRAINT [PK_Lig_Avct] PRIMARY KEY CLUSTERED 
(
	[id_lig_avct] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Caution_Fournie]    Script Date: 01/09/2013 15:16:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Caution_Fournie](
	[date_caution] [datetime] NULL,
	[montant] [float] NULL,
	[id_fiche_st] [int] NOT NULL,
	[ID_CAUTION_FOURNIE] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Caution_Fournie] PRIMARY KEY CLUSTERED 
(
	[ID_CAUTION_FOURNIE] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_Caution_Fournie_Fiche_St]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Caution_Fournie]  WITH CHECK ADD  CONSTRAINT [FK_Caution_Fournie_Fiche_St] FOREIGN KEY([id_fiche_st])
REFERENCES [dbo].[Fiche_St] ([id_fiche_st])
GO
ALTER TABLE [dbo].[Caution_Fournie] CHECK CONSTRAINT [FK_Caution_Fournie_Fiche_St]
GO
/****** Object:  ForeignKey [FK_Conducteur_Chantier]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Conducteur]  WITH CHECK ADD  CONSTRAINT [FK_Conducteur_Chantier] FOREIGN KEY([id_chantier])
REFERENCES [dbo].[Chantier] ([id_chantier])
GO
ALTER TABLE [dbo].[Conducteur] CHECK CONSTRAINT [FK_Conducteur_Chantier]
GO
/****** Object:  ForeignKey [FK_Fiche_St_Conducteur]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_St]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_St_Conducteur] FOREIGN KEY([id_conducteur])
REFERENCES [dbo].[Conducteur] ([id_conducteur])
GO
ALTER TABLE [dbo].[Fiche_St] CHECK CONSTRAINT [FK_Fiche_St_Conducteur]
GO
/****** Object:  ForeignKey [FK_Fiche_St_Ref_DDE_Agrement]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_St]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_St_Ref_DDE_Agrement] FOREIGN KEY([id_dde_agrement])
REFERENCES [dbo].[Ref_DDE_Agrement] ([id_dde_agrement])
GO
ALTER TABLE [dbo].[Fiche_St] CHECK CONSTRAINT [FK_Fiche_St_Ref_DDE_Agrement]
GO
/****** Object:  ForeignKey [FK_Fiche_St_Ref_Decenale]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_St]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_St_Ref_Decenale] FOREIGN KEY([id_decenale])
REFERENCES [dbo].[Ref_Decenale] ([id_decenale])
GO
ALTER TABLE [dbo].[Fiche_St] CHECK CONSTRAINT [FK_Fiche_St_Ref_Decenale]
GO
/****** Object:  ForeignKey [FK_Fiche_St_Ref_DGD_Presente]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_St]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_St_Ref_DGD_Presente] FOREIGN KEY([id_dgd_presente])
REFERENCES [dbo].[Ref_DGD_Presente] ([id_dgd_presente])
GO
ALTER TABLE [dbo].[Fiche_St] CHECK CONSTRAINT [FK_Fiche_St_Ref_DGD_Presente]
GO
/****** Object:  ForeignKey [FK_Fiche_St_Ref_Mode_Paiement]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_St]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_St_Ref_Mode_Paiement] FOREIGN KEY([id_mode_paiement])
REFERENCES [dbo].[Ref_Mode_Paiement] ([id_mode_paiement])
GO
ALTER TABLE [dbo].[Fiche_St] CHECK CONSTRAINT [FK_Fiche_St_Ref_Mode_Paiement]
GO
/****** Object:  ForeignKey [FK_Fiche_St_Ref_Type_Lot]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_St]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_St_Ref_Type_Lot] FOREIGN KEY([id_type_lot])
REFERENCES [dbo].[Ref_Type_Lot] ([id_type_lot])
GO
ALTER TABLE [dbo].[Fiche_St] CHECK CONSTRAINT [FK_Fiche_St_Ref_Type_Lot]
GO
/****** Object:  ForeignKey [FK_Fiche_St_Societe]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_St]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_St_Societe] FOREIGN KEY([id_societe])
REFERENCES [dbo].[Societe] ([id_societe])
GO
ALTER TABLE [dbo].[Fiche_St] CHECK CONSTRAINT [FK_Fiche_St_Societe]
GO
/****** Object:  ForeignKey [FK_Fiche_Transfert_PP_Chantier]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_Transfert_PP]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_Transfert_PP_Chantier] FOREIGN KEY([id_chantier])
REFERENCES [dbo].[Chantier] ([id_chantier])
GO
ALTER TABLE [dbo].[Fiche_Transfert_PP] CHECK CONSTRAINT [FK_Fiche_Transfert_PP_Chantier]
GO
/****** Object:  ForeignKey [FK_Fiche_Transfert_PP_Ref_Transfert_PP]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Fiche_Transfert_PP]  WITH CHECK ADD  CONSTRAINT [FK_Fiche_Transfert_PP_Ref_Transfert_PP] FOREIGN KEY([id_transfert_pp])
REFERENCES [dbo].[Ref_Transfert_PP] ([id_transfert_pp])
GO
ALTER TABLE [dbo].[Fiche_Transfert_PP] CHECK CONSTRAINT [FK_Fiche_Transfert_PP_Ref_Transfert_PP]
GO
/****** Object:  ForeignKey [FK_Lig_Avct_Fiche_St]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lig_Avct]  WITH CHECK ADD  CONSTRAINT [FK_Lig_Avct_Fiche_St] FOREIGN KEY([id_fiche_st])
REFERENCES [dbo].[Fiche_St] ([id_fiche_st])
GO
ALTER TABLE [dbo].[Lig_Avct] CHECK CONSTRAINT [FK_Lig_Avct_Fiche_St]
GO
/****** Object:  ForeignKey [FK_Lig_Penalite_Fiche_St]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lig_Penalite]  WITH CHECK ADD  CONSTRAINT [FK_Lig_Penalite_Fiche_St] FOREIGN KEY([id_fiche_st])
REFERENCES [dbo].[Fiche_St] ([id_fiche_st])
GO
ALTER TABLE [dbo].[Lig_Penalite] CHECK CONSTRAINT [FK_Lig_Penalite_Fiche_St]
GO
/****** Object:  ForeignKey [FK_Lig_Retenue_Fiche_St]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lig_Retenue]  WITH CHECK ADD  CONSTRAINT [FK_Lig_Retenue_Fiche_St] FOREIGN KEY([id_fiche_st])
REFERENCES [dbo].[Fiche_St] ([id_fiche_st])
GO
ALTER TABLE [dbo].[Lig_Retenue] CHECK CONSTRAINT [FK_Lig_Retenue_Fiche_St]
GO
/****** Object:  ForeignKey [FK_Lig_Suiv_Gest_Fiche_St]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lig_Suiv_Gest]  WITH CHECK ADD  CONSTRAINT [FK_Lig_Suiv_Gest_Fiche_St] FOREIGN KEY([id_fiche_st])
REFERENCES [dbo].[Fiche_St] ([id_fiche_st])
GO
ALTER TABLE [dbo].[Lig_Suiv_Gest] CHECK CONSTRAINT [FK_Lig_Suiv_Gest_Fiche_St]
GO
/****** Object:  ForeignKey [FK_Lig_Suiv_Gest_Ref_Statut]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lig_Suiv_Gest]  WITH CHECK ADD  CONSTRAINT [FK_Lig_Suiv_Gest_Ref_Statut] FOREIGN KEY([id_statut])
REFERENCES [dbo].[Ref_Statut] ([id_statut])
GO
ALTER TABLE [dbo].[Lig_Suiv_Gest] CHECK CONSTRAINT [FK_Lig_Suiv_Gest_Ref_Statut]
GO
/****** Object:  ForeignKey [FK_Lig_Suiv_Gest_Ref_Type_Budj_Conf]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lig_Suiv_Gest]  WITH CHECK ADD  CONSTRAINT [FK_Lig_Suiv_Gest_Ref_Type_Budj_Conf] FOREIGN KEY([id_type_budg_conf])
REFERENCES [dbo].[Ref_Type_Budj_Conf] ([id_type_budg_conf])
GO
ALTER TABLE [dbo].[Lig_Suiv_Gest] CHECK CONSTRAINT [FK_Lig_Suiv_Gest_Ref_Type_Budj_Conf]
GO
/****** Object:  ForeignKey [FK_Lig_Suiv_Gest_Ref_Type_Mch_AV]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lig_Suiv_Gest]  WITH CHECK ADD  CONSTRAINT [FK_Lig_Suiv_Gest_Ref_Type_Mch_AV] FOREIGN KEY([id_type_mch_av])
REFERENCES [dbo].[Ref_Type_Mch_AV] ([id_type_mch_av])
GO
ALTER TABLE [dbo].[Lig_Suiv_Gest] CHECK CONSTRAINT [FK_Lig_Suiv_Gest_Ref_Type_Mch_AV]
GO
/****** Object:  ForeignKey [FK_Lot_Chantier]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Lot]  WITH CHECK ADD  CONSTRAINT [FK_Lot_Chantier] FOREIGN KEY([id_chantier])
REFERENCES [dbo].[Chantier] ([id_chantier])
GO
ALTER TABLE [dbo].[Lot] CHECK CONSTRAINT [FK_Lot_Chantier]
GO
/****** Object:  ForeignKey [FK_Role_Chantier]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Role]  WITH CHECK ADD  CONSTRAINT [FK_Role_Chantier] FOREIGN KEY([id_chantier])
REFERENCES [dbo].[Chantier] ([id_chantier])
GO
ALTER TABLE [dbo].[Role] CHECK CONSTRAINT [FK_Role_Chantier]
GO
/****** Object:  ForeignKey [FK_Role_UTILISATEUR_GRP]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Role]  WITH CHECK ADD  CONSTRAINT [FK_Role_UTILISATEUR_GRP] FOREIGN KEY([id_utilisateur_grp])
REFERENCES [dbo].[UTILISATEUR_GRP] ([id_utilisateur_grp])
GO
ALTER TABLE [dbo].[Role] CHECK CONSTRAINT [FK_Role_UTILISATEUR_GRP]
GO
/****** Object:  ForeignKey [FK_Societe_Chantier]    Script Date: 01/09/2013 15:16:39 ******/
ALTER TABLE [dbo].[Societe]  WITH CHECK ADD  CONSTRAINT [FK_Societe_Chantier] FOREIGN KEY([id_chantier])
REFERENCES [dbo].[Chantier] ([id_chantier])
GO
ALTER TABLE [dbo].[Societe] CHECK CONSTRAINT [FK_Societe_Chantier]
GO
