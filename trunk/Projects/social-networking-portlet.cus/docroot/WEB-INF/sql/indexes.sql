create index IX_5A19D99E on SNW_MeetupsEntry (companyId);
create index IX_4F3C6684 on SNW_MeetupsEntry (userId);

create index IX_9CF3823D on SNW_MeetupsRegistration (meetupsEntryId);
create index IX_96DE3423 on SNW_MeetupsRegistration (meetupsEntryId, status);
create index IX_16B16977 on SNW_MeetupsRegistration (userId, meetupsEntryId);

create index IX_58A5ACFF on SNW_WallEntry (groupId);
create index IX_B8219639 on SNW_WallEntry (groupId, userId);
create index IX_A33A4145 on SNW_WallEntry (userId);

create index IX_A56E51DD on SN_MeetupsEntry (companyId);
create index IX_6EA9EEA5 on SN_MeetupsEntry (userId);

create index IX_A79293FC on SN_MeetupsRegistration (meetupsEntryId);
create index IX_BCEB16E2 on SN_MeetupsRegistration (meetupsEntryId, status);
create index IX_3CBE4C36 on SN_MeetupsRegistration (userId, meetupsEntryId);

create index IX_5C68C960 on SN_WallEntry (groupId);
create index IX_F2F6C19A on SN_WallEntry (groupId, userId);
create index IX_C46194C4 on SN_WallEntry (userId);