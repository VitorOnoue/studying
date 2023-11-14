public class ProgramaNetFlix {
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
    
    public ProgramaNetFlix(String id, String titulo, String show_type, String descricao, int release_year,
            String age_certification, int runtime, String[] generos, String[] production_countries, Float temporadas,
            String imdb_id, Float imdb_score, Float imdb_votes, Float tmdb_popularity, Float tmdb_score) {
        this.id = id;
        this.titulo = titulo;
        this.show_type = show_type;
        this.descricao = descricao;
        this.release_year = release_year;
        this.age_certification = age_certification;
        this.runtime = runtime;
        this.generos = generos;
        this.production_countries = production_countries;
        this.temporadas = temporadas;
        this.imdb_id = imdb_id;
        this.imdb_score = imdb_score;
        this.imdb_votes = imdb_votes;
        this.tmdb_popularity = tmdb_popularity;
        this.tmdb_score = tmdb_score;
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
