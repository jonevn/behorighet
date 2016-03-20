alter table anvandare add constraint UK_4aslgfj9x9kpvs7fxgc8l4bl1  unique (anvandarnamn);
alter table rattighet add constraint UK_bh0tm23ntbux5mrtlg17wksvs  unique (namn);
alter table roll add constraint UK_aa81aum9rgm78i3j55xssvfj2  unique (namn);
alter table anvandare_roll add constraint FK_h8dlpwjfj5i7kagan3p5lbnae foreign key (roll_id) references roll (id);
alter table anvandare_roll add constraint FK_q24b79ctg1imn944whqid5fpf foreign key (anvandare_id) references anvandare (id);
alter table roll_rattighet add constraint FK_3o5gkv6657pahfineelyv2wcg foreign key (rattighet_id) references rattighet (id);
alter table roll_rattighet add constraint FK_4x6qclothg3oky01485aua1d1 foreign key (roll_id) references roll (id);