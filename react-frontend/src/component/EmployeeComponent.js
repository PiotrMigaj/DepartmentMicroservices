import React, { Component } from 'react';
import EmployeeService from '../service/EmployeeService';

class EmployeeComponent extends Component {

    constructor(props){
        super(props);
        this.state={
            employeeDto: {},
            departmentDto: {},
            organisationDto: {}
        }
    }

    componentDidMount(){
        EmployeeService.getEmployee().then((response)=>{
            this.setState({employeeDto:response.data.employeeDto})
            this.setState({departmentDto:response.data.departmentDto})
            this.setState({organisationDto:response.data.organisationDto})

            console.log(this.state.employeeDto)
            console.log(this.state.departmentDto)
            console.log(this.state.organisationDto)
        })
    }


    render() {
        return (
            <div>
                <br/>
                <br/>
                <div className='card col-md-6 offset-md-3'>
                    <h3 className='text-center card-header'>View Employee Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Employee First Name: </strong> {this.state.employeeDto.firstName} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Employee Last Name: </strong> {this.state.employeeDto.lastName} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Employee Email: </strong> {this.state.employeeDto.email} </p>
                        </div>
                    </div>
                    <h3 className='text-center card-header'>View Department Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Department Name: </strong> {this.state.departmentDto.name} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Department Description: </strong> {this.state.departmentDto.description} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Department Code: </strong> {this.state.departmentDto.code} </p>
                        </div>
                    </div>
                    <h3 className='text-center card-header'>View Organisation Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Organisation Name: </strong> {this.state.organisationDto.name} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Organisation Description: </strong> {this.state.organisationDto.description} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Organisation Code: </strong> {this.state.organisationDto.code} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Organisation creation date: </strong> {this.state.organisationDto.createdAt} </p>
                        </div>
                    </div>
                </div>
                
            </div>
        );
    }
}

export default EmployeeComponent;