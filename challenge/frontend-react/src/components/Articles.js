import React, { Component } from 'react';

class Articles extends Component {

    constructor() {
        super();
        this.state = {articles: [{}], page:1, query:""};

    }
    render() {
        const articleView = this.state.articles.map((i) =>
            <div className="article"  onClick={()=>this.props.articleSelected(i)}>
                <div className="entry__title">{i.title}</div>
                <div className="entry__body">{i.body}</div>
            </div>
        );
    return (
        <div className="rail container--left">
            <div>
                {articleView}
            </div>
        </div>
    );
  }


componentDidMount() {
    this.fetchArticles(this.props.page)
}

componentWillReceiveProps(e){
    this.fetchArticles(this.props.query, this.props.page)
}

fetchArticles(query, page){
    if(page == null) page = 0;
    if(query == null) query = "";
    fetch('/articles/find?q='+query+'&page='+page).then((res) => {
        return res.json();
    }).then((res) => {
        this.setState({articles:res});
        this.forceUpdate()
    }).catch((err) => {
        this.setState({err});
    });

}

}

export default Articles;
