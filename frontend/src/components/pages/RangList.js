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

const rows = [
  createData(1,'Frozen yoghurt', 159, 6.0, 24, 4.0),
  createData(2, 'Ice cream sandwich', 237, 9.0, 37, 4.3),
  createData(3, 'Eclair', 262, 16.0, 24, 6.0),
  createData(4,'Cupcake', 305, 3.7, 67, 4.3),
  createData(5,'Gingerbread', 356, 16.0, 49, 3.9),
];

const useStyles = makeStyles({
  table: {
    minWidth: 700,
  },
});
function RangList() {

  const classes = useStyles();



  const [walkers, setWalkers] = React.useState([]);

  {/*} React.useEffect(() => {
    fetch("/ranking/1")
      .then(data => {
        return data.json();
      })
      .then(data => {
        console.log("Data: " + data);
        setWalkers(JSON.stringify(data));
      })
      .catch(error => console.log(error));
  })*/}

  //console.log("Walkers are: " + walkers);





  return (
      <div>
        <p className="text">The first table
        </p>
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="customized table">
            <TableHead>
              <TableRow>
                {/*Ovo predstavlja gornji dio tablice koji opisuje koji podatak se predstavlja gdje*/}
                <StyledTableCell>Position</StyledTableCell>
                <StyledTableCell align={"left"}>Dessert (100g serving)</StyledTableCell>
                <StyledTableCell align="left">Calories</StyledTableCell>
                <StyledTableCell align="left">Fat&nbsp;(g)</StyledTableCell>
                <StyledTableCell align="left">Carbs&nbsp;(g)</StyledTableCell>
                <StyledTableCell align="left">Protein&nbsp;(g)</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row) => (
                  //ovo predstavlja retke u tablici koji su podatci pojedinog setaca
                  <StyledTableRow key={row.position}>
                    <StyledTableCell component="th" scope="row">
                      {row.position}
                    </StyledTableCell>
                    <StyledTableCell align={"left"}>{row.name}</StyledTableCell>
                    <StyledTableCell align="left">{row.calories}</StyledTableCell>
                    <StyledTableCell align="left">{row.fat}</StyledTableCell>
                    <StyledTableCell align="left">{row.carbs}</StyledTableCell>
                    <StyledTableCell align="left">{row.protein}</StyledTableCell>
                  </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <div className="pad">
          <p className="text">The second table
          </p>
          <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="customized table">
              <TableHead>
                <TableRow>
                  {/*Ovo predstavlja gornji dio tablice koji opisuje koji podatak se predstavlja gdje*/}
                  <StyledTableCell>Position</StyledTableCell>
                  <StyledTableCell align={"left"}>Dessert (100g serving)</StyledTableCell>
                  <StyledTableCell align="left">Calories</StyledTableCell>
                  <StyledTableCell align="left">Fat&nbsp;(g)</StyledTableCell>
                  <StyledTableCell align="left">Carbs&nbsp;(g)</StyledTableCell>
                  <StyledTableCell align="left">Protein&nbsp;(g)</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                    //ovo predstavlja retke u tablici koji su podatci pojedinog setaca
                    <StyledTableRow key={row.position}>
                      <StyledTableCell component="th" scope="row">
                        {row.position}
                      </StyledTableCell>
                      <StyledTableCell align={"left"}>{row.name}</StyledTableCell>
                      <StyledTableCell align="left">{row.calories}</StyledTableCell>
                      <StyledTableCell align="left">{row.fat}</StyledTableCell>
                      <StyledTableCell align="left">{row.carbs}</StyledTableCell>
                      <StyledTableCell align="left">{row.protein}</StyledTableCell>
                    </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </div>
        <div className="pad">
          <p className="text">The third table
          </p>
          <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="customized table">
              <TableHead>
                <TableRow>
                  {/*Ovo predstavlja gornji dio tablice koji opisuje koji podatak se predstavlja gdje*/}
                  <StyledTableCell>Position</StyledTableCell>
                  <StyledTableCell align={"left"}>Dessert (100g serving)</StyledTableCell>
                  <StyledTableCell align="left">Calories</StyledTableCell>
                  <StyledTableCell align="left">Fat&nbsp;(g)</StyledTableCell>
                  <StyledTableCell align="left">Carbs&nbsp;(g)</StyledTableCell>
                  <StyledTableCell align="left">Protein&nbsp;(g)</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                    //ovo predstavlja retke u tablici koji su podatci pojedinog setaca
                    <StyledTableRow key={row.position}>
                      <StyledTableCell component="th" scope="row">
                        {row.position}
                      </StyledTableCell>
                      <StyledTableCell align={"left"}>{row.name}</StyledTableCell>
                      <StyledTableCell align="left">{row.calories}</StyledTableCell>
                      <StyledTableCell align="left">{row.fat}</StyledTableCell>
                      <StyledTableCell align="left">{row.carbs}</StyledTableCell>
                      <StyledTableCell align="left">{row.protein}</StyledTableCell>
                    </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </div>
      </div>
  );
}


export default RangList;
