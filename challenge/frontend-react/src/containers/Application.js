import React, { Component } from 'react';
import PrimaryContainer from './PrimaryContainer'

class Application extends Component {
    constructor() {
        super();
        this.state = {authenticated: true};
    }

    changeAuthenticationState(e){
        this.setState({authenticated: true})
    }

    render() {
        let view = <PrimaryContainer/>;

    return (
        <div>
            {view}
        </div>
    );
  }
}

export default Application;
