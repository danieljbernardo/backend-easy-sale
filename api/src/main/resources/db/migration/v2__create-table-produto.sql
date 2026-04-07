create table produto(
    id bigserial primary key unique not null,
    nome text not null,
    codigo bigint not null unique,
    preco decimal not null,
    descricao text not null
);