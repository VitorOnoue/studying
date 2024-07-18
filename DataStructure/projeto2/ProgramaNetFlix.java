import java.util.regex.Pattern;

public class ProgramaNetFlix {
    // regex
    String left = Pattern.quote("[");
    String right = Pattern.quote("]");
    String squote = Pattern.quote("'");
    
    private String id;
    private String titulo;
    private String show_type;
    private String descricao;
    private int release_year;
    private String age_certification;
    private int runtime;
    private String[] generos;
    private String[] production_countries;
    private Float temporadas;
    private String imdb_id;
    private Float imdb_score;
    private Float imdb_votes;
    private Float tmdb_popularity;
    private Float tmdb_score;

    public ProgramaNetFlix() {
    }
    
    public ProgramaNetFlix(String[] data) {
        
        this.id = data[0];
        this.titulo = data[1];
        this.show_type = data[2];
        this.descricao = data[3];
        this.release_year = Integer.parseInt(data[4]);
        this.age_certification = data[5];
        this.runtime = Integer.parseInt(data[6]);
        this.generos = regex(data[7]);
        this.production_countries = regex(data[8]);
        this.temporadas = data[9] != "" ? Float.parseFloat(data[9]) : 0.0f;
        this.imdb_id = data[10];
        this.imdb_score = data[11] != "" ? Float.parseFloat(data[11]) : 0.0f;
        this.imdb_votes = data[12] != "" ? Float.parseFloat(data[12]) : 0.0f;
        this.tmdb_popularity = data[13] != "" ? Float.parseFloat(data[13]) : 0.0f;
        this.tmdb_score = data[14] != "" ? Float.parseFloat(data[14]) : 0.0f;
    }

    public String[] regex(String str){
        String[] x = str.replaceAll(squote, "").replaceAll(left, "").replaceAll(right, "").replaceAll(" ", "").split(",");
        return x;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getShow_type() {
        return show_type;
    }
    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getRelease_year() {
        return release_year;
    }
    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
    public String getAge_certification() {
        return age_certification;
    }
    public void setAge_certification(String age_certification) {
        this.age_certification = age_certification;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public String[] getGeneros() {
        return generos;
    }
    public void setGeneros(String[] generos) {
        this.generos = generos;
    }
    public String[] getProduction_countries() {
        return production_countries;
    }
    public void setProduction_countries(String[] production_countries) {
        this.production_countries = production_countries;
    }
    public Float getTemporadas() {
        return temporadas;
    }
    public void setTemporadas(Float temporadas) {
        this.temporadas = temporadas;
    }
    public String getImdb_id() {
        return imdb_id;
    }
    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
    public Float getImdb_score() {
        return imdb_score;
    }
    public void setImdb_score(Float imdb_score) {
        this.imdb_score = imdb_score;
    }
    public Float getImdb_votes() {
        return imdb_votes;
    }
    public void setImdb_votes(Float imdb_votes) {
        this.imdb_votes = imdb_votes;
    }
    public Float getTmdb_popularity() {
        return tmdb_popularity;
    }
    public void setTmdb_popularity(Float tmdb_popularity) {
        this.tmdb_popularity = tmdb_popularity;
    }
    public Float getTmdb_score() {
        return tmdb_score;
    }
    public void setTmdb_score(Float tmdb_score) {
        this.tmdb_score = tmdb_score;
    }    
}
