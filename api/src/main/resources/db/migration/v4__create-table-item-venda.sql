create table item_venda(
    id bigserial primary key unique not null,
    venda_id bigint not null,
    produto_id bigint not null,
    quantidade bigint not null,
    preco_unitario decimal not null,
    subtotal decimal not null,

    constraint fk_item_venda foreign key (venda_id) references venda(id),
    constraint fk_item_produto foreign key (produto_id) references produto(id)
);