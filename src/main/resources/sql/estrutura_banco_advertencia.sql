

CREATE TABLE "postgres"."public"."aluno" (
                "id" INTEGER NOT NULL,
                "data_cadastro" TIMESTAMP,
                "nome" VARCHAR(255),
                "sexo" CHAR(1),
                "usuario_id" INTEGER,
                "registro_aluno" INTEGER,
                "escola" INTEGER,
                CONSTRAINT "aluno_pkey" PRIMARY KEY ("id")
);


CREATE SEQUENCE "postgres"."public"."bairro_id_seq";

CREATE TABLE "postgres"."public"."bairro" (
                "id" INTEGER DEFAULT nextval('advertencia.bairro_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."bairro_id_seq"'),
                "nome" VARCHAR(255),
                "cidade" INTEGER,
                CONSTRAINT "bairro_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."bairro_id_seq" OWNED BY "postgres"."public"."bairro"."id";

CREATE SEQUENCE "postgres"."public"."cidade_id_seq";

CREATE TABLE "postgres"."public"."cidade" (
                "id" INTEGER DEFAULT nextval('advertencia.cidade_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."cidade_id_seq"'),
                "nome" VARCHAR(255),
                "estado" INTEGER,
                CONSTRAINT "cidade_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."cidade_id_seq" OWNED BY "postgres"."public"."cidade"."id";

CREATE SEQUENCE "postgres"."public"."escola_id_seq";

CREATE TABLE "postgres"."public"."escola" (
                "id" INTEGER DEFAULT nextval('advertencia.escola_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."escola_id_seq"'),
                "nome" VARCHAR(255),
                "bairro" INTEGER,
                CONSTRAINT "escola_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."escola_id_seq" OWNED BY "postgres"."public"."escola"."id";

CREATE SEQUENCE "postgres"."public"."escola_funcionario_id_seq";

CREATE TABLE "postgres"."public"."escola_funcionario" (
                "id" INTEGER DEFAULT nextval('advertencia.escola_funcionario_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."escola_funcionario_id_seq"'),
                "escola" INTEGER,
                "funcionario" INTEGER,
                CONSTRAINT "escola_funcionario_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."escola_funcionario_id_seq" OWNED BY "postgres"."public"."escola_funcionario"."id";

CREATE SEQUENCE "postgres"."public"."estado_id_seq";

CREATE TABLE "postgres"."public"."estado" (
                "id" INTEGER DEFAULT nextval('advertencia.estado_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."estado_id_seq"'),
                "nome" VARCHAR(255),
                "sigla" VARCHAR(255),
                CONSTRAINT "estado_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."estado_id_seq" OWNED BY "postgres"."public"."estado"."id";

CREATE TABLE "postgres"."public"."funcionario" (
                "id" INTEGER NOT NULL,
                "data_cadastro" TIMESTAMP,
                "nome" VARCHAR(255),
                "sexo" CHAR(1),
                "usuario_id" INTEGER,
                "registro_funcionario" INTEGER,
                CONSTRAINT "funcionario_pkey" PRIMARY KEY ("id")
);


DROP TABLE "postgres"."public"."hibernate_sequences";

CREATE SEQUENCE "postgres"."public"."perfil_id_seq";

CREATE TABLE "postgres"."public"."perfil" (
                "id" INTEGER DEFAULT nextval('advertencia.perfil_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."perfil_id_seq"'),
                "nome" VARCHAR(255),
                "permissao" VARCHAR(255),
                CONSTRAINT "perfil_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."perfil_id_seq" OWNED BY "postgres"."public"."perfil"."id";

CREATE TABLE "postgres"."public"."responsavel" (
                "id" INTEGER NOT NULL,
                "data_cadastro" TIMESTAMP,
                "nome" VARCHAR(255),
                "sexo" CHAR(1),
                "usuario_id" INTEGER,
                "contato" VARCHAR(255),
                "profissao" VARCHAR(255),
                "registro_responsavel" INTEGER,
                CONSTRAINT "responsavel_pkey" PRIMARY KEY ("id")
);


CREATE SEQUENCE "postgres"."public"."responsavel_aluno_id_seq";

CREATE TABLE "postgres"."public"."responsavel_aluno" (
                "id" INTEGER DEFAULT nextval('advertencia.responsavel_aluno_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."responsavel_aluno_id_seq"'),
                "aluno" INTEGER,
                "responsavel" INTEGER,
                CONSTRAINT "responsavel_aluno_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."responsavel_aluno_id_seq" OWNED BY "postgres"."public"."responsavel_aluno"."id";

CREATE SEQUENCE "postgres"."public"."tb_advertencia_id_seq";

CREATE TABLE "postgres"."public"."tb_advertencia" (
                "id" INTEGER DEFAULT nextval('advertencia.tb_advertencia_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."tb_advertencia_id_seq"'),
                "data_emissao" TIMESTAMP,
                "texto" VARCHAR(255),
                "aluno" INTEGER,
                "funcionario" INTEGER,
                "tipo_advertencia" INTEGER,
                CONSTRAINT "tb_advertencia_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."tb_advertencia_id_seq" OWNED BY "postgres"."public"."tb_advertencia"."id";

CREATE SEQUENCE "postgres"."public"."tipo_advertencia_id_seq";

CREATE TABLE "postgres"."public"."tipo_advertencia" (
                "id" INTEGER DEFAULT nextval('advertencia.tipo_advertencia_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."tipo_advertencia_id_seq"'),
                "nivel" INTEGER,
                "nome" VARCHAR(255),
                CONSTRAINT "tipo_advertencia_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."tipo_advertencia_id_seq" OWNED BY "postgres"."public"."tipo_advertencia"."id";

CREATE SEQUENCE "postgres"."public"."usuario_id_seq";

CREATE TABLE "postgres"."public"."usuario" (
                "id" INTEGER DEFAULT nextval('advertencia.usuario_id_seq'::regclass) NOT NULL DEFAULT nextval('"postgres"."public"."usuario_id_seq"'),
                "login_usuario" VARCHAR(255),
                "senha_usuario" VARCHAR(255),
                "perfil" INTEGER,
                CONSTRAINT "usuario_pkey" PRIMARY KEY ("id")
);


ALTER SEQUENCE "postgres"."public"."usuario_id_seq" OWNED BY "postgres"."public"."usuario"."id";

ALTER TABLE "postgres"."public"."responsavel_aluno" ADD CONSTRAINT "fks8cynbrgr4dyhrnlwmam1162p"
FOREIGN KEY ("aluno")
REFERENCES "postgres"."public"."aluno" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."tb_advertencia" ADD CONSTRAINT "fkc4pi9ef57eamr0vpemm6o7v5q"
FOREIGN KEY ("aluno")
REFERENCES "postgres"."public"."aluno" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."escola" ADD CONSTRAINT "fkd0e80wjhd61w8x4l37s4n1yeg"
FOREIGN KEY ("bairro")
REFERENCES "postgres"."public"."bairro" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."bairro" ADD CONSTRAINT "fkq9yy6u3x2q4dj8xtgc9roj1ug"
FOREIGN KEY ("cidade")
REFERENCES "postgres"."public"."cidade" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."aluno" ADD CONSTRAINT "fk7ni0v35hhsrvrexji8x4438uv"
FOREIGN KEY ("escola")
REFERENCES "postgres"."public"."escola" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."escola_funcionario" ADD CONSTRAINT "fkd5chry99e5o2je1rk293y8x29"
FOREIGN KEY ("escola")
REFERENCES "postgres"."public"."escola" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."cidade" ADD CONSTRAINT "fkedwmmd3jtkssgrwyugrindb7j"
FOREIGN KEY ("estado")
REFERENCES "postgres"."public"."estado" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."escola_funcionario" ADD CONSTRAINT "fk56oib4jqmg6agh7bti8fp31bk"
FOREIGN KEY ("funcionario")
REFERENCES "postgres"."public"."funcionario" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."tb_advertencia" ADD CONSTRAINT "fkjy5bhr6xnjpfx2qwcv0by3hky"
FOREIGN KEY ("funcionario")
REFERENCES "postgres"."public"."funcionario" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."usuario" ADD CONSTRAINT "fkd2qs9yv3i1ui4wva0lqap3qow"
FOREIGN KEY ("perfil")
REFERENCES "postgres"."public"."perfil" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."responsavel_aluno" ADD CONSTRAINT "fkcywbfxlilctd0kyame86s7lf"
FOREIGN KEY ("responsavel")
REFERENCES "postgres"."public"."responsavel" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."tb_advertencia" ADD CONSTRAINT "fkcsfp91m4qepgemhs7gj9q2xtc"
FOREIGN KEY ("tipo_advertencia")
REFERENCES "postgres"."public"."tipo_advertencia" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."aluno" ADD CONSTRAINT "fk_p3aj1urfrd6mo5m2ycjlae8g7"
FOREIGN KEY ("usuario_id")
REFERENCES "postgres"."public"."usuario" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."funcionario" ADD CONSTRAINT "fk_p0htu9jav6vq5l5dliq4muqg7"
FOREIGN KEY ("usuario_id")
REFERENCES "postgres"."public"."usuario" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE "postgres"."public"."responsavel" ADD CONSTRAINT "fk_3hmgnfeca6borbnejwltixvg8"
FOREIGN KEY ("usuario_id")
REFERENCES "postgres"."public"."usuario" ("id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
