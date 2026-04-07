create table nota_fiscal(
    numero_nota bigserial primary key unique not null,
    venda_id bigint not null,
    subtotal decimal not null,
    data_emissao date not null,

    constraint fk_nota_venda foreign key (venda_id) references venda(id)
);