package myapplication.day34.beans;
       public class InfoMessages {
         public  String time;
         public String keywords;
         public String title;
         public long id;
           public String data;

    public InfoMessages() {
    }

       public InfoMessages(String time, String keywords, String title, long id, String data) {
           this.time = time;
           this.keywords = keywords;
           this.title = title;
           this.id = id;
           this.data = data;
       }

       public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

           public String getData() {
               return data;
           }

           public void setData(String data) {
               this.data = data;
           }
       }
