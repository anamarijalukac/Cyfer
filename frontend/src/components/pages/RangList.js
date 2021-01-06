import React from 'react';
import '../../App.css';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import "./RangList.css";

const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);

function createData(position, name, calories, fat, carbs, protein) {
  return { position, name, calories, fat, carbs, protein };
}

/*
const rows = [
  createData(1,'Frozen yoghurt', 159, 6.0, 24, 4.0),
  createData(2, 'Ice cream sandwich', 237, 9.0, 37, 4.3),
  createData(3, 'Eclair', 262, 16.0, 24, 6.0),
  createData(4,'Cupcake', 305, 3.7, 67, 4.3),
  createData(5,'Gingerbread', 356, 16.0, 49, 3.9),
];

 */

const useStyles = makeStyles({
  table: {
    minWidth: 700,
  },
});
function RangList() {

  const classes = useStyles();

  const [walkers1, setWalkers1] = React.useState([]);

  React.useEffect(() => {
    fetch("/ranking/1")
      .then(data => {
        return data.json();
      })
      .then(data => {
        setWalkers1(Object.entries(data));
      })
      .catch(error => console.log(error));
  }, [walkers1.length]);




  const [walkers2, setWalkers2] = React.useState([]);

  React.useEffect(() => {
    fetch("/ranking/2")
      .then(data => {
        return data.json();
      })
      .then(data => {
        setWalkers2(Object.entries(data));
      })
      .catch(error => console.log(error));
  }, [walkers2.length]);




  const [walkers3, setWalkers3] = React.useState([]);

  React.useEffect(() => {
    fetch("/ranking/3")
      .then(data => {
        return data.json();
      })
      .then(data => {
        setWalkers3(Object.entries(data));
      })
      .catch(error => console.log(error));
  }, [walkers3.length]);



  return (
    <div>
      <h1 className={"title"}>
        Rang liste šetača zadnjih mjesec dana
        </h1>
      <div style={{ margin: '70px' }}>
        <p className="text">Rang lista šetača po duljini
        </p>
        <Table class="table table-hover table-striped" style={{ margin: 'auto', backgroundColor: 'white' }}>
          <thead class="thead-dark">
            <tr>
              <th scope="col">Položaj</th>
              <th scope="col">Ime šetača</th>
              <th scope="col">Duljina</th>
            </tr>
          </thead>
          <tbody>
            {
              walkers1.map((row, index) => (
                <tr key={index}>
                  <th scope="row">
                    {index + 1}
                  </th>
                  <td>{row[0]}</td>
                  <td>{row[1]}</td>
                </tr>
              ))
            }

          </tbody>
        </Table>
      </div>
      <div style={{ margin: '70px' }}>
        <p className="text">Rang lista šetača po broju šetnji
          </p>
        <Table class="table table-hover table-striped" style={{ margin: 'auto', backgroundColor: 'white' }}>
          <thead class="thead-dark">
            <tr>
              <th scope="col">Položaj</th>
              <th scope="col">Ime šetača</th>
              <th scope="col">Broj šetnji</th>
            </tr>
          </thead>
          <tbody>
            {
              walkers2.map((row, index) => (
                <tr key={index}>
                  <th scope="row">
                    {index + 1}
                  </th>
                  <td>{row[0]}</td>
                  <td>{row[1]}</td>
                </tr>
              ))
            }

          </tbody>
        </Table>
      </div>
      <div style={{ margin: '70px' }}>
        <p className="text">Rang lista šetača po broju šetanih pasa
          </p>
        <Table class="table table-hover table-striped" style={{margin: 'auto', backgroundColor: 'white' }}>
          <thead class="thead-dark">
            <tr>
              <th scope="col">Položaj</th>
              <th scope="col">Ime šetača</th>
              <th scope="col">Broj šetanih pasa</th>
            </tr>
          </thead>
          <tbody>
            {
              walkers1.map((row, index) => (
                <tr key={index}>
                  <th scope="row">
                    {index + 1}
                  </th>
                  <td>{row[0]}</td>
                  <td>{row[1]}</td>
                </tr>
              ))
            }

          </tbody>
        </Table>
      </div>
    </div>
  );
}


export default RangList;
