import React, { Component } from 'react'
import Articles from '../components/Articles'
import MessageThread from '../components/ArticleThread'
import MessageEditor from '../components/ArticleEditor'

class PrimaryContainer extends Component {

    constructor(){
        super();
        this.state = {article:{body:""}};

    }
    render() {
    return (
      <div>
          <div className="nav">TextIQ Take Home Assignment</div>
          <Articles articleSelected={this.articleSelected.bind(this)} page={this.state.page} query={this.state.query}/>
          <div className="container">
              <div id="articleContainer" className="container--border-left">
                  <MessageThread article={this.state.article}/>
              </div>
          </div>
          <div id="contentButtons" className="container--border-left">
              <MessageEditor pageUp={this.pageUp.bind(this)} pageDown={this.pageDown.bind(this)} search={this.search.bind(this)}/>
          </div>
      </div>
    );
  }

  articleSelected(i){
      this.setState({article:i});
  }

  search(query){
      this.setState({query:query});
  }

  pageUp(){
      if(this.state.page == null) this.state.page = 0;
      this.setState({page:this.state.page + 1});
  }

  pageDown(){
      if(this.state.page == null) this.state.page = 0;
      if(this.state.page <= 0){
          this.setState({page:0});
          return;
      }
      this.setState({page:this.state.page - 1});
  }
}

export default PrimaryContainer;
