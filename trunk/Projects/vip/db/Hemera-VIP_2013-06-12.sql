use [Hemera-VIP]
go
insert into FIE_FIELD (fie_form_field_id, fie_label, lag_id, ent_id, fie_db_field, fie_group, fie_order) values ('txtDescription', 'Description de la d�l�gation', 1, '015', 'description', 'D�l�gation', 1)
insert into FIE_FIELD (fie_form_field_id, fie_label, lag_id, ent_id, fie_db_field, fie_group, fie_order) values ('txtDescription', 'Description de la d�l�gation', 1, '016', 'description', 'D�l�gation', 1)
go
ALTER TABLE DOM_DOCUMENT_MODEL ADD dom_sub_del_filename varchar(255)
