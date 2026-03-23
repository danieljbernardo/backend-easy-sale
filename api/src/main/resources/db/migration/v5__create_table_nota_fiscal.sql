create table nota_fiscal(
    id bigserial primary key unique not null,
    venda_id bigint not null,
    numero_nota bigint unique not null,
    serie bigint not null,
    data_emissao date not null,

    constraint fk_nota_venda foreign key (venda_id) references venda(id)
);