import React from 'react';
import InsightImg from './assets/Insight.jpg';

class Insights extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userID: "sample",
        }
    }

    render(){



        return(
            <div style={{
                backgroundImage: "url(" + InsightImg + ")",
                backgroundPosition: 'center',
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat'
            }}>
            <br/><br/><br/><br/><br/><br/><br/><br/><br/>
            <center>
           <h1>Sorry Mate !!!<br/>Try Pro for Amazing Insights</h1>
           </center>
           <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
          </div>
        )
    }
}
export default Insights