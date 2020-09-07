import React from 'react';

class Portfolio extends React.Component {

    constructor(props) {
      super(props);
      this.state = {
       userID : "sample",
       portfolio : null,
      };
     
    }

    componentDidMount() {
        //CALL REST
        var url = 'http://localhost:8983/userService/portfolio/'+ this.state.userID
        fetch(url, {
            method: 'GET', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                this.setState({portfolio:data})
                console.log('Success Portfolio:', data);
            })
            .catch((error) => {
                this.setState({portfolio:null})
                console.error('Error:', error);
            });
    }

    
    render(){
        return(
    <div>
        List of stocks
        
        {
            (this.state.portfolio == null) ?null:
            <div>
                {this.state.portfolio.map(
                    (e)=>(
                        <div>
                            {e.stock_name}
                            {e.stock_volume}
                        </div>
                    )
                )}
            </div>
        }
    </div>
        )
    }
}
export default Portfolio;