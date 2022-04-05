package br.unibh.sdm.posts_services.entidades;

import java.util.Date;
import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * @author 120241946
 *
 */
@DynamoDBTable(tableName = "posts")
public class Post {

	private String id;
	private Date data;
	private String nome;
	private String descricao;
	private String imagen;
	private String cidade;
	private String estado;
	private Number telefone;
	public Post(String id, Date data, String nome, String descricao, String imagen, String cidade, String estado,
			Number telefone) {
		super();
		this.id = id;
		this.data = data;
		this.nome = nome;
		this.descricao = descricao;
		this.imagen = imagen;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
	}
	public Post() {
		super();
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", data=" + data + ", nome=" + nome + ", descricao=" + descricao + ", imagen="
				+ imagen + ", cidade=" + cidade + ", estado=" + estado + ", telefone=" + telefone + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(cidade, data, descricao, estado, id, imagen, nome, telefone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(data, other.data)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(estado, other.estado)
				&& Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}
	@DynamoDBHashKey
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@DynamoDBAttribute
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@DynamoDBAttribute
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@DynamoDBAttribute
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@DynamoDBAttribute
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@DynamoDBAttribute
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	@DynamoDBAttribute
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@DynamoDBAttribute
	public Number getTelefone() {
		return telefone;
	}
	public void setTelefone(Number telefone) {
		this.telefone = telefone;
	}
	
	
}
