INSERT INTO advertencia.estado(id, nome, sigla) VALUES (1, 'Acre', 'AC');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (2, 'Alagoas',	'AL');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (3, 'Amapá',	'AP');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (4, 'Amazonas', 'AM');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (5, 'Bahia',	'BA');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (6, 'Ceará',	'CE');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (7, 'Distrito Federal', 'DF');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (8, 'Espírito Santo', 'ES');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (9, 'Goiás', 'GO');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (10, 'Maranhão',	'MA');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (11, 'Mato Grosso', 'MT');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (12, 'Mato Grosso do Sul',	'MS');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (13, 'Minas Gerais',	'MG');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (14, 'Pará',	'PA');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (15, 'Paraíba', 'PB');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (16, 'Paraná', 'PR');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (17, 'Pernambuco', 'PE');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (18, 'Piauí', 'PI');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (19, 'Rio de Janeiro', 'RJ');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (20, 'Rio Grande do Norte', 'RN');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (21, 'Rio Grande do Sul', 'RS');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (22, 'Rondônia', 'RO');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (23, 'Roraima', 'RR');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (24, 'Santa Catarina',	'SC');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (25, 'São Paulo', 'SP');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (26, 'Sergipe', 'SE');
INSERT INTO advertencia.estado(id, nome, sigla) VALUES (27, 'Tocantins', 'TO');

INSERT INTO advertencia.cidade(id, nome, estado) VALUES (1, 'Campo Grande', 12);

INSERT INTO advertencia.bairro(id, nome, cidade) VALUES (1, 'Itanhangá Park', 1);

INSERT INTO advertencia.escola(id, nome, bairro) VALUES (1, 'Escola Estadual Hércules Maymone', 1);

-- 1 (visualizar), 1 (inserir), 1 (alterar), 1 (remover)
INSERT INTO advertencia.perfil(id, nome, permissao) VALUES (1, 'Administrador', '1111');
INSERT INTO advertencia.perfil(id, nome, permissao) VALUES (2, 'Super Usuário', '1010');
INSERT INTO advertencia.perfil(id, nome, permissao) VALUES (3, 'Usuário', '1000');

-- '1d2800d129be132c3c789c2fe09d99f4' is 'silva2016', password of josesilva
INSERT INTO advertencia.usuario(id, login_usuario, senha_usuario, perfil) VALUES (1, 'josesilva', '1d2800d129be132c3c789c2fe09d99f4', 1);

INSERT INTO advertencia.funcionario(id, data_cadastro, nome, sexo, usuario_id, registro_funcionario) VALUES (1, '2016-01-01', 'José Silva', 'M', 1, 5050);

INSERT INTO advertencia.escola_funcionario(id, escola, funcionario) VALUES (1, 1, 1);

INSERT INTO advertencia.aluno(id, data_cadastro, nome, sexo, usuario_id, registro_aluno, escola) VALUES (1, '2016-01-01', 'Pedro Castro', 'M', null, 147888, 1);

INSERT INTO advertencia.responsavel(id, data_cadastro, nome, sexo, usuario_id, contato, profissao, registro_responsavel) VALUES (1, '2016-01-01', 'Ana Castro', 'F', null, 'anacastro@teste.com', 'Enfermeira', 56333);

INSERT INTO advertencia.responsavel_aluno(id, aluno, responsavel) VALUES (1, 1, 1);

INSERT INTO advertencia.tipo_advertencia(id, nivel, nome) VALUES (1, 10, 'Primária');
INSERT INTO advertencia.tipo_advertencia(id, nivel, nome) VALUES (2, 20, 'Secundária');
INSERT INTO advertencia.tipo_advertencia(id, nivel, nome) VALUES (3, 30, 'Aviso Final');

INSERT INTO advertencia.tb_advertencia(id, data_emissao, texto, aluno, funcionario, tipo_advertencia) VALUES (1, '2016-01-01', 'Jogou lixo no vaso sanitário', 1, 1, 1);
