import React, { Component } from 'react';

class ArticleThread extends Component {

  constructor(){
      super();
      this.state = {article:{body:"value"}};
  }

  render() {
    return (
        <div>
            <div className="article--full">{this.props.article.body}</div>
        </div>
    );
  }
}

export default ArticleThread;
