import React from 'react';
import './App.css';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      table: [null,null,null, null,null,null, null,null,null],
      count: 0
    }
  }

  render() {
    return (
      <div className="cross-zero-game">
        <div className="table-grid" onClick={this.onClickGrid} index="0">{this.state.table[0]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="1">{this.state.table[1]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="2">{this.state.table[2]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="3">{this.state.table[3]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="4">{this.state.table[4]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="5">{this.state.table[5]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="6">{this.state.table[6]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="7">{this.state.table[7]}</div>
        <div className="table-grid" onClick={this.onClickGrid} index="8">{this.state.table[8]}</div>
      </div>
    );
  }

  onClickGrid = event => {
    const index = event.target.getAttribute("index");
    if (this.state.table[index] === null) {
      const char = (this.state.count % 2 === 0) ? 'X' : 'O';
      let table = this.state.table;
      table[index] = char;
      this.setState({
        table: table,
        count: this.state.count + 1 });
      this.checkWinner(char);
    }
    else {
      alert("Недопустимый ход!");
    }
  }

  checkWinner = char => {
    console.log("checkWinner" + char);
    const lines = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6]
    ];
    const table = this.state.table;
    for (let i = 0; i < lines.length; i++) {
      const line = lines[i];
      if (table[line[0]] === char && table[line[1]] === char && table[line[2]] === char) {
        alert("Победил " + char);
        break;
      }
    }
  }

}
export default App;
