import React, { Component } from 'react';

class ArticleEditor extends Component {
    constructor() {
        super();
        this.handleSearch = this.handleSearch.bind(this);
        this.handleQuery = this.handleQuery.bind(this);
        this.state = {query:""};

    }
  render() {
    return (
      <div>
          <div className="button_group">
              <button onClick={()=>this.props.pageUp()}> Next </button>
              <button onClick={()=>this.props.pageDown()}> Previous </button>
          </div>
          <div className="searchGroup">
              <input id="searchBox" value={this.state.query} onChange={this.handleSearch}/>
              <button onClick={this.handleQuery}>Send</button>
          </div>
      </div>
    );
  }

    handleSearch(e,f){
        this.setState({query: e.currentTarget.value});
    }

    handleQuery(e){
        this.props.search(this.state.query)
    }
}

export default ArticleEditor;
