package br.com.almavivasolutions.integrador.model.entity.certificado;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;
import br.com.almavivasolutions.integrador.model.entity.Fabricante;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CERTIFICADO_EM_AVALIACAO")
public class CertificadoAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CERTIFICADO")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO_CERTIFICADO")
    private String tipoCertificado;

    @Column(name = "DATA_REALIZACAO")
    private LocalDate dataRealizacao;

    @Column(name = "DATA_EXPIRACAO")
    private LocalDate dataExpiracao;

    @Column(name = "PRAZO_VALIDADE")
    private String prazoValidade;

    @Column(name = "ID_CERTIFICADO_FABRICANTE")
    private String idCertificadoFabricante;

    @Column(name = "REGIAO")
    private String regiao;

    @Column(name = "PARTNER_ACCOUNT")
    private String partnerAccount;

    @Column(name = "URL_ARQUIVO")
    private String urlArquivo;

    @Column(name = "VALIDO")
    private Boolean valido;

    @Column(name = "PENDENTE")
    private Boolean pendente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COLABORADOR", nullable = false)
    private Colaborador colaborador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_FABRICANTE", nullable = false)
    private Fabricante fabricante;

    @Column(name = "SUB_TIPO_CERTIFICADO")
    private String subTipoCertificado;

    @Column(name = "DATA_LIMITE_RENOVACAO")
    private LocalDate dataLimiteRenovacao;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "OBSERVACAO")
    private String observacao;

	public Boolean getPendente() {
		return pendente;
	}

	public void setPendente(Boolean pendente) {
		this.pendente = pendente;
	}

	private static CertificadoAvaliacao instance;
	
	public static CertificadoAvaliacao getInstance() {
		if(instance == null) {
			instance = new CertificadoAvaliacao();
		}
		
		instance.clear();
		return instance;
	}
	
	private void clear() {
		nome = null;
		email = null;
		observacao = null;
		tipoCertificado = null;
		subTipoCertificado = null;
		dataRealizacao = null;
		dataExpiracao = null;
		dataLimiteRenovacao = null;
		prazoValidade = null;
		idCertificadoFabricante = null;
		regiao = null;
		partnerAccount = null;
		urlArquivo = null;
		valido = null;
		colaborador =  null;
		fabricante = null;
	}
	
	public CertificadoAvaliacao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipoCertificado() {
		return tipoCertificado;
	}

	public void setTipoCertificado(String tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}

	public String getSubTipoCertificado() {
		return subTipoCertificado;
	}

	public void setSubTipoCertificado(String subTipoCertificado) {
		this.subTipoCertificado = subTipoCertificado;
	}

	public LocalDate getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(LocalDate dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDate dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public LocalDate getDataLimiteRenovacao() {
		return dataLimiteRenovacao;
	}

	public void setDataLimiteRenovacao(LocalDate dataLimiteRenovacao) {
		this.dataLimiteRenovacao = dataLimiteRenovacao;
	}

	public String getPrazoValidade() {
		return prazoValidade;
	}

	public void setPrazoValidade(String prazoValidade) {
		this.prazoValidade = prazoValidade;
	}

	public String getIdCertificadoFabricante() {
		return idCertificadoFabricante;
	}

	public void setIdCertificadoFabricante(String idCertificadoFabricante) {
		this.idCertificadoFabricante = idCertificadoFabricante;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getPartnerAccount() {
		return partnerAccount;
	}

	public void setPartnerAccount(String partnerAccount) {
		this.partnerAccount = partnerAccount;
	}

	public String getUrlArquivo() {
		return urlArquivo;
	}

	public void setUrlArquivo(String urlArquivo) {
		this.urlArquivo = urlArquivo;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "\nCertificadoAvaliacao{" +
				"\nid=" + id +
				", \nnome='" + nome + '\'' +
				", \ntipoCertificado='" + tipoCertificado + '\'' +
				", \ndataRealizacao=" + dataRealizacao +
				", \ndataExpiracao=" + dataExpiracao +
				", \nprazoValidade='" + prazoValidade + '\'' +
				", \nidCertificadoFabricante='" + idCertificadoFabricante + '\'' +
				", \nregiao='" + regiao + '\'' +
				", \npartnerAccount='" + partnerAccount + '\'' +
				", \nurlArquivo='" + urlArquivo + '\'' +
				", \nvalido=" + valido +
				", \npendente=" + pendente +
				", \ncolaborador=" + colaborador +
				", \nfabricante=" + fabricante +
				", \nsubTipoCertificado='" + subTipoCertificado + '\'' +
				", \ndataLimiteRenovacao=" + dataLimiteRenovacao +
				", \nemail='" + email + '\'' +
				", \nobservacao='" + observacao + '\'' +
				'}';
	}
}